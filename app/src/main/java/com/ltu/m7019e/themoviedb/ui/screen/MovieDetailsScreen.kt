package com.ltu.m7019e.themoviedb.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ltu.m7019e.themoviedb.database.Movies
import com.ltu.m7019e.themoviedb.navigation.Screen
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModel

@Composable
fun MovieDetailsScreen(navController: NavHostController, viewModel: MovieDBViewModel, movieId: Long, apiKey: String) {
    val uiState by viewModel.uiState.collectAsState()
    val movie = uiState.selectedMovie
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        if (movie != null) {
            Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Genres:", style = MaterialTheme.typography.titleMedium)
            Text(text = movie.genres.joinToString(", "))

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Homepage:", style = MaterialTheme.typography.titleMedium)
            Text(
                text = movie.homepage,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.homepage))
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Open in IMDb App:", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "https://www.imdb.com/title/${movie.imdbId}/",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    val imdbIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("imdb://title/${movie.imdbId}")
                        setPackage("com.imdb.mobile")
                    }
                    if (imdbIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(imdbIntent)
                    } else {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/${movie.imdbId}/"))
                        context.startActivity(webIntent)
                    }
                }
            )
        } else {
            Text(text = "Movie not found.")
        }

        Button(
            onClick = {
                navController.navigate(Screen.MovieReview.createRoute(movieId))
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("View Reviews & Trailers")
        }
    }
}
