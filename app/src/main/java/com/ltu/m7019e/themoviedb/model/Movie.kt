package com.ltu.m7019e.themoviedb.model

data class Movie(
    var id: Long = 0L,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var overview: String,
    var genres: List<String>,
    var homepage: String,
    var imdbId: String
)
