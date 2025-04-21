package com.ltu.m7019e.themoviedb.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ltu.m7019e.themoviedb.navigation.Screen
import com.ltu.m7019e.themoviedb.ui.MovieDetailsScreen
//import com.ltu.m7019e.themoviedb.ui.MovieListScreen
import com.ltu.m7019e.themoviedb.ui.ThirdScreen
import com.ltu.m7019e.themoviedb.ui.screen.MovieGridScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    movieList: List<Movie>
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            MovieGridScreen(
                movieList = movieList,
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetails.createRoute(movieId))
                }
            )
        }

        composable(
            route = Screen.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { defaultValue = 0L })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getLong("movieId") ?: 0L
            MovieDetailsScreen(movieId = movieId, navController = navController)
        }

        composable(route = Screen.ThirdScreen.route) {
            ThirdScreen()
        }
    }
}
