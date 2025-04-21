    package com.ltu.m7019e.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ltu.m7019e.themoviedb.database.Movies
import com.ltu.m7019e.themoviedb.ui.theme.TheMovieDBTheme
import androidx.navigation.compose.rememberNavController
import com.ltu.m7019e.themoviedb.model.AppNavGraph


    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                TheMovieDBTheme {
                    Surface(
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
        val movieList = Movies().getMovies()

        AppNavGraph(navController = navController, movieList = movieList)

    }