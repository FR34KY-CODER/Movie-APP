package com.example.splashscreen

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_detail)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movie", Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("movie")
        }

        if (movie == null) {
            Toast.makeText(this, "Movie data missing!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.backdropPath ?: movie.posterPath}")
            .into(findViewById(R.id.imgBackdrop))

        findViewById<TextView>(R.id.tvTitle).text = movie.title
        findViewById<TextView>(R.id.tvInfo).text =
            "${movie.releaseDate.take(4)} • ⭐ ${movie.rating}"
        findViewById<TextView>(R.id.tvOverview).text = movie.overview
    }
}