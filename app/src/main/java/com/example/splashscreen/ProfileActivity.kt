package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private val apiKey by lazy { getString(R.string.tmdb_api_key) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val setupRecycler: (RecyclerView, suspend ()->MovieResponse) -> Unit = { rv, fetch ->
            rv.layoutManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
            lifecycleScope.launch {
                try {
                    val resp = fetch()
                    rv.adapter = MovieAdapter(resp.movies) { movie ->
                        startActivity(Intent(this@ProfileActivity, MovieDetailActivity::class.java)
                            .apply { putExtra("movie", movie) })
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ProfileActivity, "Error loading", Toast.LENGTH_SHORT).show()
                }
            }
        }

        setupRecycler(findViewById(R.id.rvTrending)) { TMDB.service.getTrending(apiKey) }
        setupRecycler(findViewById(R.id.rvTopRated)) { TMDB.service.getTopRated(apiKey) }
        setupRecycler(findViewById(R.id.rvPopular)) { TMDB.service.getPopular(apiKey) }
    }
}