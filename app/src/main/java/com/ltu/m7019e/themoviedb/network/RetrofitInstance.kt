package com.ltu.m7019e.themoviedb.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import com.ltu.m7019e.themoviedb.utils.Constants
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json

object RetrofitInstance {

    val api: MovieDBApiService by lazy {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
            .create(MovieDBApiService::class.java)
    }
}