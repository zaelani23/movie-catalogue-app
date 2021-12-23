package com.zaelani.moviecatalogue.ui.home.movie

import androidx.lifecycle.ViewModel
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies() = movieCatalogueRepository.getMovies()
}