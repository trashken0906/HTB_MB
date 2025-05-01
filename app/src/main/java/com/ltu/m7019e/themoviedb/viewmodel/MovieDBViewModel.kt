package com.ltu.m7019e.themoviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.ltu.m7019e.themoviedb.data.MovieDBUIState
import com.ltu.m7019e.themoviedb.model.MovieDetail
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.network.RetrofitInstance
import com.ltu.m7019e.themoviedb.utils.SECRETS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class MovieDBViewModel : ViewModel() {

    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    private val _uiState = MutableStateFlow(MovieDBUIState())
    val uiState: StateFlow<MovieDBUIState> = _uiState.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail: StateFlow<MovieDetail?> = _movieDetail

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies(SECRETS.API_KEY)
                _movieList.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setSelectedMovie(movie: Movie) {
        _uiState.update { currentState ->
            currentState.copy(selectedMovie = movie)
        }
    }

    fun fetchMovieDetail(movieId: Long, apiKey: String) {
        viewModelScope.launch {
            try {
                val detail = RetrofitInstance.api.getMovieDetail(movieId, apiKey)
                _movieDetail.value = detail
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}