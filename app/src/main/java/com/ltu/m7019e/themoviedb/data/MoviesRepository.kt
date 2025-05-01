package com.ltu.m7019e.themoviedb.data

import com.ltu.m7019e.themoviedb.model.Movie

interface MoviesRepository {
    suspend fun fetchPopularMovies(): List<Movie>
}



