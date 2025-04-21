package com.ltu.m7019e.themoviedb.network

interface TMDBApi {
    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): ReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): VideoResponse
}