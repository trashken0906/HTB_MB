package com.ltu.m7019e.themoviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.themoviedb.model.Review
import com.ltu.m7019e.themoviedb.model.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieReviewViewModel : ViewModel {
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    fun fetchMovieDetails(movieId: Long, apiKey: String) {
        viewModelScope.launch {
            try {
                val reviewResponse = tmdbApi.getReviews(movieId, apiKey)
                _reviews.value = reviewResponse.results

                val videoResponse = tmdbApi.getVideos(movieId, apiKey)
                _videos.value = videoResponse.results.filter { it.site == "YouTube" }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }
}