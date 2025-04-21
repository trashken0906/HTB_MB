package com.ltu.m7019e.themoviedb.network

object RetrofitInstance {
    object RetrofitInstance {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: TMDBApi by lazy {
            retrofit.create(TMDBApi::class.java)
        }
    }
}