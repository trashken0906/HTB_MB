package com.ltu.m7019e.themoviedb.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.utils.Constants

@Composable
fun MovieGridScreen(
    movieList: List<Movie>,
    onMovieClick: (Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(movieList) { movie ->
            MovieGridItem(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )
        }
    }
}


@Composable
fun MovieGridItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()  // Ensure items take full available width
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp), // Set image height
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


