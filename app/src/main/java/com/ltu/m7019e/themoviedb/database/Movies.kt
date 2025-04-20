package com.ltu.m7019e.themoviedb.database

import com.ltu.m7019e.themoviedb.model.Movie

class Movies {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                title = "A Minecraft Movie",
                posterPath = "/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
                backdropPath = "/is9bmV6uYXu7LjZGJczxrjJDlv8.jpg",
                releaseDate = "2025-03-31",
                overview = "By day, they're invisible—valets, hostesses, and bartenders at a luxury hotel. By night, they're the Carjackers, a crew of skilled drivers who track and rob wealthy clients on the road. As they plan their ultimate heist, the hotel director hires a ruthless hitman, to stop them at all costs. With danger closing in, can Nora, Zoe, Steve, and Prestance pull off their biggest score yet?",
                genres = listOf("Action", "Heist", "Thriller"),
                homepage = "https://example.com/minecraft-movie",
                imdbId = "tt3566834"
            ),
            Movie(
                id = 2,
                title = "Captain America: Brave New World",
                posterPath = "/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
                backdropPath = "/gsQJOfeW45KLiQeEIsom94QPQwb.jpg",
                releaseDate = "2025-02-12",
                overview = "When a group of radical activists take over an energy company's annual gala, seizing 300 hostages, an ex-soldier turned window cleaner suspended 50 storeys up on the outside of the building must save those trapped inside, including her younger brother.",
                genres = listOf("Action", "Drama"),
                homepage = "https://example.com/captain-america",
                imdbId = "tt14513804"
            ),
            Movie(
                id = 3,
                title = "Gunslingers",
                posterPath = "/O7REXWPANWXvX2jhQydHjAq2DV.jpg",
                backdropPath = "/ce3prrjh9ZehEl5JinNqr4jIeaB.jpg",
                releaseDate = "2025-04-11",
                overview = "When the most wanted man in America surfaces in a small Kentucky town, his violent history -- and a blood-thirsty mob seeking vengeance and a king’s ransom -- soon follow. As brothers face off against one another and bullets tear the town to shreds, this lightning-fast gunslinger makes his enemies pay the ultimate price for their greed.",
                genres = listOf("Western", "Crime", "Action"),
                homepage = "https://example.com/gunslingers",
                imdbId = "tt24850708"
            ),
            Movie(
                id = 4,
                title = "Batman Ninja vs. Yakuza League",
                posterPath = "/sVVT6GYFErVv0Lcc9NvqCu0iOxO.jpg",
                backdropPath = "/hwlyY7LJdEFbCPaGNXiskKKmJ5X.jpg",
                releaseDate = "2025-03-17",
                overview = "The Batman family has returned to the present to discover that Japan has disappeared, and a giant island - Hinomoto - is now in the sky over Gotham City. At the top sit the Yakuza, a group of superpowered individuals who reign without honor or humanity and look suspiciously like the Justice League. Now, it’s up to Batman and his allies to save Gotham!",
                genres = listOf("Animation", "Superhero", "Sci-Fi"),
                homepage = "https://example.com/batman-ninja",
                imdbId = "tt32508210"
            ),
            Movie(
                id = 5,
                title = "Peter Pan's Neverland Nightmare",
                posterPath = "/mOR1Ks0EcXocwMV4EPv4letz0F5.jpg",
                backdropPath = "/6van4BavoNXaZhCPdzLHNQ4Uc8H.jpg",
                releaseDate = "2025-01-13",
                overview = "Wendy Darling strikes out in an attempt to rescue her brother Michael from the clutches of the evil Peter Pan who intends to send him to Neverland. Along the way she meets a twisted Tinkerbell, who is hooked on what she thinks is fairy dust.",
                genres = listOf("Horror", "Fantasy", "Adventure"),
                homepage = "https://example.com/neverland-nightmare",
                imdbId = "tt21955520"
            )
        )
    }
}
