package com.example.splashscreen

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDB {
    val service: TMDBService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBService::class.java)
    }
}