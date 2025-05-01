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
import androidx.compose.runtime.*
import com.ltu.m7019e.themoviedb.navigation.Screen
import com.ltu.m7019e.themoviedb.viewmodel.MovieDBViewModel




@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    viewModel: MovieDBViewModel,
    movieId: Long,
    apiKey: String
) {
    val movie by viewModel.movieDetail.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetail(movieId, apiKey)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (movie != null) {
            Text(text = movie!!.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Genres:", style = MaterialTheme.typography.titleMedium)
            Text(text = movie!!.genres.joinToString(", ") { it.name })

            Spacer(modifier = Modifier.height(8.dp))

            movie!!.homepage?.let {
                Text(text = "Homepage:", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (movie!!.imdbId.isNullOrBlank()) {
                Text(
                    text = "No IMDb link available",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                movie!!.imdbId?.let { imdb ->
                    Text(text = "IMDb Link:", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "https://www.imdb.com/title/$imdb",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            val imdbIntent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("imdb://title/$imdb")
                                setPackage("com.imdb.mobile")
                            }
                            if (imdbIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(imdbIntent)
                            } else {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://www.imdb.com/title/$imdb")
                                    )
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                navController.navigate(Screen.MovieReview.createRoute(movieId))
            }) {
                Text("View Reviews & Trailers")
            }

        } else {
            CircularProgressIndicator(modifier = Modifier.padding(top = 32.dp))
        }
    }
}
