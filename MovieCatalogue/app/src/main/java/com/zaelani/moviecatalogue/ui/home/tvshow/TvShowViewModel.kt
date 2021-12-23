package com.zaelani.moviecatalogue.ui.home.tvshow

import androidx.lifecycle.ViewModel
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getTvShow() = movieCatalogueRepository.getTvShows()

}