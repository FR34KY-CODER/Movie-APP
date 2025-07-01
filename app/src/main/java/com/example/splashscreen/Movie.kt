package com.example.splashscreen

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("original_title") val title: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val rating: Float
) : Parcelable

data class MovieResponse(
    @SerializedName("results") val movies: List<Movie>
)
