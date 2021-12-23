package com.zaelani.moviecatalogue.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieCatalogueDao {

    @Query("SELECT * FROM movie_entities WHERE isFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFav = 1")
    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavoriteMovie(movies: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavoriteTvShow(tvShows: TvShowEntity)

    @Delete
    fun deleteFavoriteMovie(movie: MovieEntity)

    @Delete
    fun deleteFavoriteTvShow(tvShow: TvShowEntity)
}