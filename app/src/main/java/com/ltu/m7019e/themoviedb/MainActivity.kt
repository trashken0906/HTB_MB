    package com.ltu.m7019e.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.ltu.m7019e.themoviedb.ui.theme.TheMovieDBTheme
import androidx.navigation.compose.rememberNavController
import com.ltu.m7019e.themoviedb.navigation.AppNavGraph
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModel


    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
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


    AppNavGraph(navController = navController, movieList = movieList, viewModel = viewModel)
}



