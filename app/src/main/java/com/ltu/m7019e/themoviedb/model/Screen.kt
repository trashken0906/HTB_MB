package com.ltu.m7019e.themoviedb.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MovieDetails : Screen("movie_details/{movieId}") {
        fun createRoute(movieId: Long) = "movie_details/$movieId"
    }
    object ThirdScreen : Screen("third_screen")
}
