package com.ltu.m7019e.themoviedb.database

import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.model.MovieDetail

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        overview = overview,
        genreIds = emptyList() // not stored in MovieEntity, default to empty
    )
}

fun Movie.toMovieEntity(viewType: String): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath ?: "",
        backdropPath = backdropPath ?: "",
        releaseDate = releaseDate,
        overview = overview,
        homepage = "", // default if not available from Movie model
        imdbId = "",   // default if not available from Movie model
        viewType = viewType
    )
}

// Mapping between Movie and FavoriteMovieEntity
fun FavoriteMovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        overview = overview,
        genreIds = emptyList()
    )
}

fun Movie.toFavoriteMovieEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = id,
        title = title,
        posterPath = posterPath ?: "",
        backdropPath = backdropPath ?: "",
        releaseDate = releaseDate,
        overview = overview,
        homepage = "", // default if not available
        imdbId = ""    // default if not available
    )
}

fun MovieDetail.toFavoriteEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = this.id,
        title = this.title,
        posterPath = "",
        backdropPath = "",
        releaseDate = this.releaseDate.orEmpty(),
        overview = this.overview.orEmpty(),
        homepage = this.homepage.orEmpty(),
        imdbId = this.imdbId.orEmpty()
    )
}