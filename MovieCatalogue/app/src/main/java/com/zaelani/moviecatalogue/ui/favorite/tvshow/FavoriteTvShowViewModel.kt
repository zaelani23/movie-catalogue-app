package com.zaelani.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository

class FavoriteTvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel()  {
    fun getFavTvShows() = movieCatalogueRepository.getFavoriteTvShows()
}