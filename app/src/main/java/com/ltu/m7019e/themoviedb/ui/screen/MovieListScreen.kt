package com.ltu.m7019e.themoviedb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.utils.Constants
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale

@Composable
fun MovieListScreen(movieList: List<Movie>, onMovieClick: (Long) -> Unit) {
    LazyColumn {
        items(movieList) { movie ->
            MovieListItemCard(movie = movie, onClick = { onMovieClick(movie.id) })
        }
    }
}

@Composable
fun MovieListItemCard(movie: Movie, onClick: () -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick() }) {
        Row {
            AsyncImage(
                model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .width(92.dp)
                    .height(138.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = movie.releaseDate, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
