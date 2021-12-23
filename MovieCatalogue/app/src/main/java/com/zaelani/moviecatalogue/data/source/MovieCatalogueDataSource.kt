package com.zaelani.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity

interface MovieCatalogueDataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getTvShows(): LiveData<List<TvShowEntity>>

    fun getMovie(movieEntity: MovieEntity): LiveData<MovieEntity>
    fun getTvShow(tvShowEntity: TvShowEntity): LiveData<TvShowEntity>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)

    fun deleteFavoriteMovie(movie: MovieEntity)
    fun deleteFavoriteTvShow(tvShow: TvShowEntity)
}