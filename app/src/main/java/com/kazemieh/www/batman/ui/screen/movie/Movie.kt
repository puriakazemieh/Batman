package com.kazemieh.www.batman.ui.screen.movie

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.kazemieh.www.batman.R
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import com.kazemieh.www.batman.ui.commen.Loading3Dots
import com.kazemieh.www.batman.ui.theme.gray
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Movie(
    navController: NavHostController,
    imdbID: String,
    viewModel: MovieViewModel = hiltViewModel()
) {
    var movie by remember {
        mutableStateOf(Movie())
    }

    var loading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        viewModel.getMovieById(id = imdbID)
        viewModel.getMovieById.collectLatest { movieResult ->
            Log.d("949494", "AmazingOfferSection: $movieResult")
            when (movieResult) {
                is ApiResult.Success -> {
                    movie = movieResult.data
                    loading = false
                }

                is ApiResult.Error -> {
                    loading = false
                }

                is ApiResult.Loading -> {
                    loading = true
                }
            }
        }
    }
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Loading3Dots()
        }
    } else {
        LazyColumn(
            modifier = Modifier.background(colorScheme.surface)
        ) {
            Log.d("949494", "AmazingOfferSection: movie= $movie")
            item {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = movie.Poster,
                        placeholder = painterResource(id = R.drawable.batman)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    contentDescription = movie.Title,
                    contentScale = ContentScale.FillWidth
                )
            }

            movie.Title?.let {
                item {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            movie.Plot?.let {
                item {
                    Text(
                        text = it,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }


            movie.Actors?.let { item { Detail("Actors:", it) } }
            movie.Awards?.let { item { Detail("Awards:", it) } }
            movie.BoxOffice?.let { item { Detail("BoxOffice:", it) } }
            movie.Country?.let { item { Detail("Country:", it) } }
            movie.DVD?.let { item { Detail("DVD:", it) } }
            movie.Director?.let { item { Detail("Director:", it) } }
            movie.Genre?.let { item { Detail("Genre:", it) } }
            movie.Language?.let { item { Detail("Language:", it) } }
            movie.Metascore?.let { item { Detail("Metascore:", it) } }
            movie.Plot?.let { item { Detail("Plot:", it) } }
            movie.Poster?.let { item { Detail("Poster:", it) } }
            movie.Production?.let { item { Detail("Production:", it) } }
            movie.Rated?.let { item { Detail("Rated:", it) } }

            movie.Ratings?.let {
                it.forEach { rating ->
                    item { Detail("rating Source: (${rating.Source}):", rating.Value) }
                }
            }

            movie.Released?.let { item { Detail("Released:", it) } }
            movie.Response?.let { item { Detail("Response:", it) } }
            movie.Runtime?.let { item { Detail("Runtime:", it) } }
            movie.Title?.let { item { Detail("Title:", it) } }
            movie.Type?.let { item { Detail("Type:", it) } }
            movie.Website?.let { item { Detail("Website:", it) } }
            movie.Writer?.let { item { Detail("Writer:", it) } }
            movie.Year?.let { item { Detail("Year:", it) } }
            movie.imdbRating?.let { item { Detail("imdbRating:", it) } }
            movie.imdbVotes?.let { item { Detail("imdbVotes:", it) } }

        }
    }
}

@Composable
fun Detail(key: String, value: String) {
    Row {
        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = key,
                fontWeight = FontWeight.Medium,
            )
        }

        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                fontWeight = FontWeight.SemiBold,
                text = value,
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colorScheme.gray)
            )
        }
    }
}