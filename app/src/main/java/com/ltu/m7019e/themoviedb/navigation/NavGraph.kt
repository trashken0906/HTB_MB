package com.ltu.m7019e.themoviedb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.ui.MovieDetailsScreen
import com.ltu.m7019e.themoviedb.ui.screen.MovieGridScreen
import com.ltu.m7019e.themoviedb.ui.screen.MovieReviewScreen
import com.ltu.m7019e.themoviedb.utils.SECRETS
import com.ltu.m7019e.themoviedb.utils.SECRETS.API_KEY
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModel

@Composable
fun AppNavGraph(navController: NavHostController,
                movieList: List<Movie>,
                viewModel: MovieDBViewModel,
                currentViewType: String,
                onViewTypeChange: (String) -> Unit) {
    val isOffline by viewModel.isOffline.collectAsState()

    NavHost(navController = navController, startDestination = Screen.Home.route)
    {
        //Home = Grid screen
        composable(route = Screen.Home.route) {
            MovieGridScreen(
                movieList = movieList,
                isOffline = isOffline,
                onMovieClick = { movieId ->
                    val selected = movieList.find { it.id == movieId }
                    if (selected != null)
                        viewModel.setSelectedMovie(selected)
                    navController.navigate(Screen.MovieDetails.createRoute(movieId))
                },
                currentViewType = currentViewType,
                onViewTypeChange = onViewTypeChange
            )
        }

        composable(
            route = Screen.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getLong("movieId") ?: 0L
            val apiKey = API_KEY

            MovieDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                movieId = movieId,
                apiKey = apiKey
            )
        }


        // Movie Review & Video Screen
        composable(
            route = Screen.MovieReview.route,
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getLong("movieId") ?: 0L
            MovieReviewScreen(
                movieId = movieId,
                apiKey = API_KEY
            )
        }
    }
}


