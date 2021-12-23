package com.zaelani.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository

class FavoriteMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getFavMovies() = movieCatalogueRepository.getFavoriteMovies()
}