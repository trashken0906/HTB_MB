package com.ltu.m7019e.themoviedb.database

import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.model.MovieDetail
import com.ltu.m7019e.themoviedb.network.MovieDBApiService

class MovieRepository (
    private val movieDao: MovieDao,
    private val apiService: MovieDBApiService
) {
    suspend fun fetchMoviesFromNetwork(viewType: String, apiKey: String): List<Movie> {
        val response = when (viewType) {
            "popular" -> apiService.getPopularMovies(apiKey)
            "top_rated" -> apiService.getTopRatedMovies(apiKey)
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }

        // Clear previous types and cache new list
        movieDao.deleteOtherTypes(viewType)
        val entities = response.results.map { it.toMovieEntity(viewType) }
        movieDao.insertAll(entities)

        return response.results

    }

    suspend fun getCachedMovies(viewType: String): List<Movie> {
        val entities = movieDao.getMoviesByType(viewType)
        return entities.map { it.toMovie() }
    }

    suspend fun getMovieDetail(id: Long, apiKey: String): MovieDetail {
        return apiService.getMovieDetail(id, apiKey)
    }

    suspend fun saveFavorite(movie: FavoriteMovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun removeFavorite(movieId: Long) {
        movieDao.deleteMovie(movieId)
    }

    suspend fun getFavoriteMovies(): List<FavoriteMovieEntity> {
        return movieDao.getSavedMovies()
    }

    suspend fun isFavorite(movieId: Long): Boolean {
        return movieDao.getMovie(movieId) != null
    }
}