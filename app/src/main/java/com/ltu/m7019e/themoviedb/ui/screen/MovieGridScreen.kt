package com.ltu.m7019e.themoviedb.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.utils.Constants

@Composable
fun MovieGridScreen(
    movieList: List<Movie>,
    isOffline: Boolean,
    onMovieClick: (Long) -> Unit,
    currentViewType: String,
    onViewTypeChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(8.dp).zIndex(1f)) {
        Button(onClick = { expanded = true }) {
            Text("Showing: ${currentViewType.replaceFirstChar { it.uppercase() }}")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.zIndex(2f)) {
            DropdownMenuItem(onClick = {
                onViewTypeChange("popular")
                expanded = false
            }, text = { Text("Popular") })

            DropdownMenuItem(onClick = {
                onViewTypeChange("top_rated")
                expanded = false
            }, text = { Text("Top Rated") })

            DropdownMenuItem(onClick = {
                onViewTypeChange("favorites")
                expanded = false
            }, text = { Text("Favorites") })
        }
    }

    //Spacer(modifier = Modifier.height(8.dp))

    if (isOffline && currentViewType != "favorites") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Internet Connection",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(movieList) { movie ->
                MovieGridItem(
                    movie = movie,
                    onClick = { onMovieClick(movie.id) }
                )
            }
        }
    }

}


@Composable
fun MovieGridItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            var hasError by remember { mutableStateOf(false) }

            val posterUrl = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath.orEmpty()
            Log.d("PosterURL", "Movie: ${movie.title}, URL: $posterUrl")
            Log.d("RawMovie", movie.toString())

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                if (hasError) {
                    Text(
                        text = "Image not available",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    AsyncImage(
                        model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath.orEmpty(),
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize(),
                        onError = {
                            hasError = true
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}



