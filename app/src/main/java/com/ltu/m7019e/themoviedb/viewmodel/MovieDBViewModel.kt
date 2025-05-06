package com.ltu.m7019e.themoviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.themoviedb.database.FavoriteMovieEntity
import com.ltu.m7019e.themoviedb.database.MovieRepository
import com.ltu.m7019e.themoviedb.database.toMovie

import com.ltu.m7019e.themoviedb.model.MovieDetail
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.network.NetworkMonitor
import com.ltu.m7019e.themoviedb.network.RetrofitInstance
import com.ltu.m7019e.themoviedb.utils.SECRETS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class MovieDBViewModel(
    private val repository: MovieRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    private val _isOffline = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail: StateFlow<MovieDetail?> = _movieDetail

    private val _currentViewType = MutableStateFlow("popular")
    val currentViewType: StateFlow<String> = _currentViewType

    fun setViewType(type: String, apiKey: String) {
        _currentViewType.value = type
        fetchMovies(apiKey)
    }

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect { connected ->
                _isOffline.value = !connected

                // Auto-fetch if connected
                if (connected) {
                    fetchMovies(SECRETS.API_KEY)
                }
            }
        }

        setViewType("popular", SECRETS.API_KEY)
    }

     /*fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies(SECRETS.API_KEY)
                _movieList.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/

    fun fetchMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                if(_currentViewType.value == "favorites"){
                    val favorites = repository.getFavoriteMovies()
                    _movieList.value = favorites.map { it.toMovie() }
                    _isOffline.value = false
                }
                else{
                    val movies = repository.fetchMoviesFromNetwork(_currentViewType.value, apiKey)
                    _movieList.value = movies
                }
            } catch (e: Exception) {
                // If API fails, fallback to cached
                val cached = repository.getCachedMovies(_currentViewType.value)
                _movieList.value = cached

                _isOffline.value = cached.isEmpty()
            }
        }
    }

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun fetchMovieDetail(movieId: Long, apiKey: String) {
        viewModelScope.launch {
            try {
                val detail = repository.getMovieDetail(movieId, apiKey)
                _movieDetail.value = detail
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveFavorite(movie: FavoriteMovieEntity) {
        viewModelScope.launch {
            repository.saveFavorite(movie)
        }
    }

    fun removeFavorite(movieId: Long) {
        viewModelScope.launch {
            repository.removeFavorite(movieId)
        }
    }

    suspend fun isFavorite(movieId: Long): Boolean {
        return repository.isFavorite(movieId)
    }

    suspend fun getFavorites(): List<FavoriteMovieEntity> {
        return repository.getFavoriteMovies()
    }
}