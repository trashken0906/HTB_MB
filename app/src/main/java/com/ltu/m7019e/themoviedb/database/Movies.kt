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
                homepage = "https://www.minecraft-movie.com",
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
                homepage = "https://www.marvel.com/movies/captain-america-brave-new-world",
                imdbId = "tt14513804"
            ),
            Movie(
                id = 3,
                title = "Batman Ninja vs. Yakuza League",
                posterPath = "/sVVT6GYFErVv0Lcc9NvqCu0iOxO.jpg",
                backdropPath = "/hwlyY7LJdEFbCPaGNXiskKKmJ5X.jpg",
                releaseDate = "2025-03-17",
                overview = "The Batman family has returned to the present to discover that Japan has disappeared, and a giant island - Hinomoto - is now in the sky over Gotham City. At the top sit the Yakuza, a group of superpowered individuals who reign without honor or humanity and look suspiciously like the Justice League. Now, it’s up to Batman and his allies to save Gotham!",
                genres = listOf("Animation", "Superhero", "Sci-Fi"),
                homepage = "https://www.warnerbros.com/movies/batman-ninja-vs-yakuza-league",
                imdbId = "tt32508210"
            ),
            Movie(
                id = 4,
                title = "Moana 2",
                posterPath = "/aLVkiINlIeCkcZIzb7XHzPYgO6L.jpg",
                backdropPath = "/o5vasl0xbZWWKQnAlaBTSgntHH2.jpg",
                releaseDate = "2024-11-21",
                overview = "After receiving an unexpected call from her wayfinding ancestors, Moana journeys alongside Maui and a new crew to the far seas of Oceania and into dangerous, long-lost waters for an adventure unlike anything she's ever faced.",
                genres = listOf("Animation", "Adventure", "Family", "Comedy"),
                homepage = "https://movies.disney.com/moana-2",
                imdbId = "tt13622970"
            ),
            Movie(
                id = 5,
                title = "Peter Pan's Neverland Nightmare",
                posterPath = "/mOR1Ks0EcXocwMV4EPv4letz0F5.jpg",
                backdropPath = "/6van4BavoNXaZhCPdzLHNQ4Uc8H.jpg",
                releaseDate = "2025-01-13",
                overview = "Wendy Darling strikes out in an attempt to rescue her brother Michael from the clutches of the evil Peter Pan who intends to send him to Neverland. Along the way she meets a twisted Tinkerbell, who is hooked on what she thinks is fairy dust.",
                genres = listOf("Horror", "Fantasy", "Adventure"),
                homepage = "https://www.itnfilms.net/catalog/peter-pan's-neverland-nightmare",
                imdbId = "tt21955520"
            )
        )
    }
}
