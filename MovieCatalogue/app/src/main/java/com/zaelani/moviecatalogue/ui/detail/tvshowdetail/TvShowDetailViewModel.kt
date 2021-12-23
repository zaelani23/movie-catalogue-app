package com.zaelani.moviecatalogue.ui.detail.tvshowdetail

import androidx.lifecycle.ViewModel
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity

class TvShowDetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {
    private lateinit var tvShow: TvShowEntity

    fun setSelectedTvShow(tvShow: TvShowEntity) {
        this.tvShow = tvShow
    }

    fun getTvShow() = movieCatalogueRepository.getTvShow(tvShow)

    fun setFavoriteTvShow(tvShow: TvShowEntity) = movieCatalogueRepository.setFavoriteTvShow(this.tvShow, state = true)

    fun deleteFavoriteTvShow(tvShow: TvShowEntity) = movieCatalogueRepository.deleteFavoriteTvShow(this.tvShow)
}