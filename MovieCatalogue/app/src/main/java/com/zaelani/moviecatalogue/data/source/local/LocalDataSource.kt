package com.zaelani.moviecatalogue.data.source.local

import androidx.paging.DataSource
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.data.source.local.room.MovieCatalogueDao

class LocalDataSource(private val mMovieCatalogueDao: MovieCatalogueDao) {

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getFavMovies()

    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogueDao.getFavTvShows()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mMovieCatalogueDao.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        mMovieCatalogueDao.setFavoriteTvShow(tvShow)
    }

    fun deleteFavoriteMovie(movie: MovieEntity){
        mMovieCatalogueDao.deleteFavoriteMovie(movie)
    }

    fun deleteFavoriteTvShow(tvShow: TvShowEntity){
        mMovieCatalogueDao.deleteFavoriteTvShow(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)
    }
}