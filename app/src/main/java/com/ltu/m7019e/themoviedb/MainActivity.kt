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
import com.ltu.m7019e.themoviedb.database.MovieDatabase
import com.ltu.m7019e.themoviedb.database.MovieRepository
import com.ltu.m7019e.themoviedb.navigation.AppNavGraph
import com.ltu.m7019e.themoviedb.network.MovieDBApiService
import com.ltu.m7019e.themoviedb.network.NetworkMonitor
import com.ltu.m7019e.themoviedb.network.RetrofitInstance
import com.ltu.m7019e.themoviedb.utils.SECRETS
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModel
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModelFactory


    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val database = MovieDatabase.getDatabase(applicationContext)
            val movieDao = database.movieDao()
            val apiService = RetrofitInstance.api

            val repository = MovieRepository(movieDao, apiService)
            val networkMonitor = NetworkMonitor(applicationContext)

            val factory = MovieDBViewModelFactory(repository, networkMonitor)

            setContent {
                val viewModel: MovieDBViewModel = viewModel(factory = factory)

                TheMovieDBTheme {
                    Surface (
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                    TheMovieDBApp(viewModel = viewModel)
                }
            }
        }
    }
    }

@Composable
fun TheMovieDBApp(viewModel: MovieDBViewModel){
    val navController = rememberNavController()
    //val viewModel: MovieDBViewModel = viewModel()
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



