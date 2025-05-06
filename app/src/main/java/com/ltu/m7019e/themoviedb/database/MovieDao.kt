package com.ltu.m7019e.themoviedb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    // For caching by type
    @Query("SELECT * FROM movies WHERE viewType = :viewType")
    suspend fun getMoviesByType(viewType: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE viewType != :viewType")
    suspend fun deleteOtherTypes(viewType: String)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    // For favorites
    @Query("SELECT * FROM favorite_movies")
    suspend fun getSavedMovies(): List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    suspend fun getMovie(id: Long): FavoriteMovieEntity?

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun deleteMovie(id: Long)
}