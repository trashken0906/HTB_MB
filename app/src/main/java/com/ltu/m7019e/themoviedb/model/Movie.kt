package com.ltu.m7019e.themoviedb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Movie(
    @SerialName("id")
    val id: Long = 0,

    @SerialName("title")
    val title: String = "",

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("release_date")
    val releaseDate: String = "",

    @SerialName("overview")
    val overview: String = "",

    @SerialName("genre_ids")
    val genreIds: List<Int> = emptyList()  // TMDB popular gives genre IDs
)