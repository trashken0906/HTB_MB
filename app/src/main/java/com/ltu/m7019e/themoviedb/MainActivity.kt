    package com.ltu.m7019e.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ltu.m7019e.themoviedb.database.Movies
import com.ltu.m7019e.themoviedb.model.Movie
import com.ltu.m7019e.themoviedb.ui.theme.TheMovieDBTheme
import com.ltu.m7019e.themoviedb.utils.Constants
import androidx.navigation.compose.rememberNavController
import com.ltu.m7019e.themoviedb.navigation.AppNavGraph


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
    val movieList = Movies().getMovies()

    AppNavGraph(navController = navController, movieList = movieList)

}

@Composable
fun MovieList(movielist: List<Movie>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(movielist){movie ->
            MovieListItemCard(
                movie = movie,
                modifier = Modifier.padding(8.dp))

        }
    }

}

@Composable
fun MovieListItemCard(movie: Movie, modifier: Modifier = Modifier){
    Card (modifier = Modifier){
        Row {
            Box {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = modifier
                        .width(92.dp)
                        .height(138.dp),
                    contentScale = ContentScale.Crop
                )

            }
            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheMovieDBTheme {
        MovieListItemCard(
            movie = Movie(
                id = 1,
                title = "A Minecraft Movie",
                posterPath = "/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
                backdropPath = "/is9bmV6uYXu7LjZGJczxrjJDlv8.jpg",
                releaseDate = "2025-03-31",
                overview = "By day, they're invisible—valets, hostesses, and bartenders at a luxury hotel. By night, they're the Carjackers, a crew of skilled drivers who track and rob wealthy clients on the road. As they plan their ultimate heist, the hotel director hires a ruthless hitman, to stop them at all costs. With danger closing in, can Nora, Zoe, Steve, and Prestance pull off their biggest score yet?",
                genres = listOf("Action", "Heist", "Thriller"),
                homepage = "https://example.com/minecraft-movie",
                imdbId = "tt1234567"))

    }
}