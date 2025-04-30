package com.ltu.m7019e.themoviedb.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ltu.m7019e.themoviedb.viewmodel.MovieReviewViewModel
import com.ltu.m7019e.themoviedb.ui.components.VideoPlayer

@Composable
fun MovieReviewScreen(
    movieId: Long,
    apiKey: String,
    viewModel: MovieReviewViewModel = viewModel()
) {
    val reviews by viewModel.reviews.collectAsState()
    val videos by viewModel.videos.collectAsState()

    // Fetch the movie details (reviews + videos) once when screen loads
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId, apiKey)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Reviews section
        Text(text = "Reviews", style = MaterialTheme.typography.headlineSmall)
        LazyRow {
            items(reviews) { review ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(250.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Author: ${review.author}")
                        Text(review.content.take(150) + "…")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Videos section
        Text(text = "Trailers", style = MaterialTheme.typography.headlineSmall)
        LazyRow {
            items(videos) { video ->
                VideoPlayer(videoKey = video.key)
            }
        }
    }
}
