package com.kazemieh.www.batman.data.remote

import com.kazemieh.www.batman.data.remote.model.BatmanResponse
import com.kazemieh.www.batman.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BatmanApiService {

    @GET(".")
    suspend fun getAllBatmanMovies(
        @Query("apikey") apikey: String,
        @Query("s") s: String
    ): BatmanResponse

    @GET
    suspend fun getMoveById(
        @Query("apikey") apikey: String,
        @Query("i") id: String
    ): MovieResponse

}