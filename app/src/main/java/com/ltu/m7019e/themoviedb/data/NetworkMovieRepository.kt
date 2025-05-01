package com.ltu.m7019e.themoviedb.data


import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.network.MovieDBApiService
import com.ltu.m7019e.themoviedb.utils.SECRETS

class NetworkMoviesRepository(
    private val apiService: MovieDBApiService
) : MoviesRepository {

    override suspend fun fetchPopularMovies(): List<Movie> {
        return apiService.getPopularMovies(SECRETS.API_KEY).results
    }
}