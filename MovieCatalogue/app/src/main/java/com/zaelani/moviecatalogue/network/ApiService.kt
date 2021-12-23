package com.zaelani.moviecatalogue.network

import com.zaelani.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.zaelani.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import com.zaelani.moviecatalogue.utils.NetworkInfo.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MoviesResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<TvShowResponse>
}