    package com.ltu.m7019e.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.ltu.m7019e.themoviedb.database.Movies
import com.ltu.m7019e.themoviedb.ui.theme.TheMovieDBTheme
import androidx.navigation.compose.rememberNavController
import com.ltu.m7019e.themoviedb.database.MovieDao
import com.ltu.m7019e.themoviedb.database.MovieRepository
import com.ltu.m7019e.themoviedb.navigation.AppNavGraph
import com.ltu.m7019e.themoviedb.network.MovieDBApiService
import com.ltu.m7019e.themoviedb.utils.SECRETS
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModel


    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //val repository = MovieRepository(apiService = MovieDBApiService, movieDao = MovieDao)
            setContent {
                TheMovieDBTheme {
                    Surface (
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                    TheMovieDBApp()
                }
            }
        }
    }
    }

@Composable
fun TheMovieDBApp(){
    val navController = rememberNavController()
    val viewModel: MovieDBViewModel = viewModel()
    val movieList by viewModel.movieList.collectAsState()
    val currentViewType by viewModel.currentViewType.collectAsState()


    AppNavGraph(
        navController = navController,
        movieList = movieList,
        viewModel = viewModel,
        currentViewType = currentViewType,
        onViewTypeChange = { newType ->
            viewModel.setViewType(newType, SECRETS.API_KEY)
        })
}



