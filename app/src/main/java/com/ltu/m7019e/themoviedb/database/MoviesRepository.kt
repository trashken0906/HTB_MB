package com.ltu.m7019e.themoviedb.database

import com.ltu.m7019e.themoviedb.model.Movie

interface MoviesRepository {
    suspend fun fetchPopularMovies(): List<Movie>
}



