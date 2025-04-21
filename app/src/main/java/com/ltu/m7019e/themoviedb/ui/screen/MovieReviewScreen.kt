package com.ltu.m7019e.themoviedb.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.ltu.m7019e.themoviedb.viewmodel.MovieDetailsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
    fun MovieReviewScreen(movieId: Long, apiKey: String, viewModel: MovieDetailsViewModel = viewModel()) {
        val reviews by viewModel.reviews.collectAsState()
        val videos by viewModel.videos.collectAsState()

        LaunchedEffect(movieId) {
            viewModel.fetchMovieDetails(movieId, apiKey)
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Reviews", style = MaterialTheme.typography.headlineSmall)
            LazyRow {
                items(reviews) { review ->
                    Card(modifier = Modifier.padding(8.dp).width(300.dp)) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text("Author: ${review.author}", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(review.content, maxLines = 3, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Trailers", style = MaterialTheme.typography.headlineSmall)
            LazyRow {
                items(videos) { video ->
                    VideoPlayer(videoKey = video.key)
                }
            }
        }
    }
}
