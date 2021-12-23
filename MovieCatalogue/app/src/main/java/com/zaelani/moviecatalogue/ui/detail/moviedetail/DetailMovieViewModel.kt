package com.zaelani.moviecatalogue.ui.detail.moviedetail

import androidx.lifecycle.ViewModel
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {
    private lateinit var movie : MovieEntity

    fun setSelectedMovie(movie : MovieEntity) {
        this.movie = movie
    }

    fun getMovie() = movieCatalogueRepository.getMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity) = movieCatalogueRepository.setFavoriteMovie(this.movie, state = true)

    fun deleteFavoriteMovie(movie: MovieEntity) = movieCatalogueRepository.deleteFavoriteMovie(this.movie)
}