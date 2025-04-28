package com.ltu.m7019e.themoviedb.network

import android.provider.SyncStateContract
import com.ltu.m7019e.themoviedb.model.MovieResponse
import com.ltu.m7019e.themoviedb.model.ReviewResponse
import com.ltu.m7019e.themoviedb.model.VideoResponse
import com.ltu.m7019e.themoviedb.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApiService {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): ReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): VideoResponse
}