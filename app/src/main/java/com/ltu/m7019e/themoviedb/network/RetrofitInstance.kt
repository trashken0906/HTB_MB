package com.ltu.m7019e.themoviedb.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ltu.m7019e.themoviedb.network.MovieDBApiService
import com.ltu.m7019e.themoviedb.utils.Constants

object RetrofitInstance {

    val api: MovieDBApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDBApiService::class.java)
    }
}