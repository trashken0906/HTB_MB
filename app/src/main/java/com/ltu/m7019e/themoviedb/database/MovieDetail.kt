package com.ltu.m7019e.themoviedb.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDetail(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String = "",
    @SerialName("overview") val overview: String = "",
    @SerialName("release_date") val releaseDate: String = "",
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("genres") val genres: List<Genre> = emptyList()
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)
