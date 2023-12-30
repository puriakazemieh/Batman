package com.kazemieh.www.batman.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import com.kazemieh.www.batman.data.db.MovieDao
import com.kazemieh.www.batman.data.db.entity.toMovie
import com.kazemieh.www.batman.data.remote.BatmanApiService
import com.kazemieh.www.batman.data.remote.model.toDpRating
import com.kazemieh.www.batman.data.remote.model.toMovieEntity
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.BatmanRepository
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BatmanRepositoryImpl @Inject constructor(
    private val batmanApiService: BatmanApiService,
    private val batmanDp: MovieDao,
    @ApplicationContext context: Context
) : BatmanRepository {


    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val isNetWorkAvailable = MutableStateFlow(false)

    // network state change listener
    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//      .removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
        .build()

    private val networkCallback: ConnectivityManager.NetworkCallback =
        object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isNetWorkAvailable.value = true
            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val cellular =
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                val wifi = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                if (cellular || wifi) {
                }
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                isNetWorkAvailable.value = false
            }
        }

    init {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private suspend inline fun <T> doWithNetwork(fetch: () -> T): T {
        return isNetWorkAvailable.first { it }.let { fetch() }
    }


    override suspend fun getAllMovies(): Flow<List<AllMoves>> {
        CoroutineScope(currentCoroutineContext()).launch {
            doWithNetwork {
                batmanApiService.getAllBatmanMovies("3e974fca", "batman").Search.map {
                    batmanDp.insertAllMovie(it.toMovieEntity())
                }
            }
        }
        return batmanDp.getAllMovie()
    }


    override suspend fun getMovieById(id: String): Flow<Movie> =
        channelFlow {
            batmanDp.getMovie(id).collectLatest {
                send(it.toMovie())
            }
        }.onStart {
            CoroutineScope(currentCoroutineContext()).launch {
                doWithNetwork {
                    batmanApiService.getMoveById(apikey = "3e974fca", id = id).let {
                        batmanDp.updateMovie(
                            imdbID = it.imdbID,
                            actors = it.Actors,
                            awards = it.Awards,
                            boxOffice = it.BoxOffice,
                            country = it.Country,
                            dvd = it.DVD,
                            director = it.Director,
                            genre = it.Genre,
                            language = it.Language,
                            metaScore = it.Metascore,
                            plot = it.Plot,
                            poster = it.Poster,
                            production = it.Production,
                            rated = it.Rated,
                            ratings = it.Ratings?.map { it.toDpRating() },
                            released = it.Released,
                            response = it.Response,
                            runtime = it.Runtime,
                            title = it.Title,
                            type = it.Type,
                            website = it.Website,
                            writer = it.Writer,
                            year = it.Year,
                            imdbRating = it.imdbRating,
                            imdbVotes = it.imdbVotes,
                        )
                    }
                }
            }
        }.catch {
            ApiResult.Error(Exception(it))
        }


}

