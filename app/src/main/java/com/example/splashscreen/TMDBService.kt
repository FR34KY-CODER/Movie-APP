package com.example.splashscreen

import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopular(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apiKey: String): MovieResponse

    @GET("trending/movie/day")
    suspend fun getTrending(@Query("api_key") apiKey: String): MovieResponse
}