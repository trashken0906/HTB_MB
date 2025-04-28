package com.ltu.m7019e.themoviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.themoviedb.model.Review
import com.ltu.m7019e.themoviedb.model.Video
import com.ltu.m7019e.themoviedb.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieReviewViewModel : ViewModel() {

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    fun fetchMovieDetails(movieId: Long, apiKey: String) {
        viewModelScope.launch {
            try {
                val reviewResponse = RetrofitInstance.api.getMovieReviews(movieId, apiKey)
                val videoResponse = RetrofitInstance.api.getMovieVideos(movieId, apiKey)

                println("Fetched Reviews: ${reviewResponse.results.size}")
                println("Fetched Videos: ${videoResponse.results.size}") // print log

                _reviews.value = reviewResponse.results
                _videos.value = videoResponse.results

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}