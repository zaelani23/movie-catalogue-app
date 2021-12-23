package com.zaelani.moviecatalogue.utils

import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.data.source.remote.response.movie.Movie
import com.zaelani.moviecatalogue.data.source.remote.response.tvshow.TvShow

object DataDummy {
    fun getMovies(): List<MovieEntity> {
            return listOf(
                    MovieEntity(
                            580489,
                            "Venom: Let There Be Carnage",
                            "2021-09-30",
                            6.8,
                            "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                            5401.308,
                            "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg"
                    ),
                    MovieEntity(
                            524434,
                            "Eternals",
                            "2021-11-03",
                            7.1,
                            "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                            3365.535,
                            "/4DiJQ1mBp4ztoznZADIrPg69v46.jpg"
                    )
            )
    }

    fun getTvShows(): List<TvShowEntity> {
        return listOf(
                TvShowEntity(
                        90462,
                        "Chucky",
                        "2021-10-12",
                        8.0,
                        "After a vintage Chucky doll turns up at a suburban yard sale, an idyllic American town is thrown into chaos as a series of horrifying murders begin to expose the town’s hypocrisies and secrets. Meanwhile, the arrival of enemies — and allies — from Chucky’s past threatens to expose the truth behind the killings, as well as the demon doll’s untold origins.",
                        6726.667,
                        "/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg"
                ),
                TvShowEntity(
                        2051,
                        "The Price Is Right",
                        "1972-09-04",
                        6.7,
                        "\"Come on down!\" The Price Is Right features a wide variety of games and contests with the same basic challenge: Guess the prices of everyday (or not-quite-everyday) retail items.",
                        3202.776,
                        "/6m4uYFAJwkanZXd0n0HUQ0lYHLl.jpg"
                )
        )
    }

    // for remote
    fun getRemoteMovies(): List<Movie> {
        return listOf(
                Movie(
                        adult = false,
                        backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                        genreIds =  listOf(14, 28, 12),
                        id = 464052,
                        originalLanguage = "en",
                        originalTitle = "Wonder Woman 1984",
                        overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                        popularity = 3342.686,
                        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        releaseDate = "2020-12-16",
                        title = "Wonder Woman 1984",
                        video = false,
                        voteAverage = 7.2,
                        voteCount = 2641
                ),
                Movie(
                        adult = false,
                        backdropPath = "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                        genreIds =  listOf(16, 35, 18, 10402, 14),
                        id = 508442,
                        originalLanguage = "en",
                        originalTitle = "Soul",
                        overview = "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                        popularity = 2849.972,
                        posterPath = "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                        releaseDate = "2020-12-25",
                        title = "Soul",
                        video = false,
                        voteAverage = 8.4,
                        voteCount = 3477
                )
        )
    }

    fun getRemoteTvShows(): List<TvShow>{
            return listOf(
                    return listOf(
                            TvShow(
                                    backdropPath = "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                                    firstAirDate = "2018-05-02",
                                    genreIds = listOf(10759, 18),
                                    id = 77169,
                                    name = "Cobra Kai",
                                    originCountry = listOf("US"),
                                    originalLanguage = "en",
                                    originalName = "Cobra Kai",
                                    overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                                    popularity = 1132.227,
                                    posterPath = "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                                    voteAverage = 8.1,
                                    voteCount = 1717
                            ),
                            TvShow(
                                    backdropPath = "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                                    firstAirDate = "2019-11-12",
                                    genreIds = listOf(10765, 10759),
                                    id = 82856,
                                    name = "The Mandalorian",
                                    originCountry = listOf("US"),
                                    originalLanguage = "en",
                                    originalName = "The Mandalorian",
                                    overview = "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                                    popularity = 1001.909,
                                    posterPath = "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                                    voteAverage = 8.5,
                                    voteCount = 5135
                            )
                    )
            )
    }


}