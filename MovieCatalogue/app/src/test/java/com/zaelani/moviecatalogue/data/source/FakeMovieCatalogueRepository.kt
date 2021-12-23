package com.zaelani.moviecatalogue.data.source


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zaelani.moviecatalogue.data.source.local.LocalDataSource
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.data.source.remote.RemoteDataSource
import com.zaelani.moviecatalogue.data.source.remote.response.movie.Movie
import com.zaelani.moviecatalogue.data.source.remote.response.tvshow.TvShow
import com.zaelani.moviecatalogue.utils.AppExecutors


class FakeMovieCatalogueRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MovieEntity(
                                    id,
                                    title,
                                    releaseDate,
                                    voteAverage,
                                    overview,
                                    popularity,
                                    posterPath
                            )
                            movieList.add(movie)
                        }
                    }
                    movieResult.postValue(movieList)
                }
            }
        })
        return movieResult
    }

    override fun getTvShows(): LiveData<List<TvShowEntity>> {
        val tvResult = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShow>?) {
                val tvList = ArrayList<TvShowEntity>()
                if (tvShows != null) {
                    for (response in tvShows) {
                        with(response) {
                            val tvShow = TvShowEntity(
                                    id,
                                    name,
                                    firstAirDate,
                                    voteAverage,
                                    overview,
                                    popularity,
                                    posterPath
                            )
                            tvList.add(tvShow)
                        }
                    }
                    tvResult.postValue(tvList)
                }
            }
        })
        return tvResult
    }

    override fun getMovie(movieEntity: MovieEntity): LiveData<MovieEntity> {
        val movie = MutableLiveData<MovieEntity>()
        movie.postValue(movieEntity)
        return movie
    }

    override fun getTvShow(tvShowEntity: TvShowEntity): LiveData<TvShowEntity> {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.postValue(tvShowEntity)
        return tvShow
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(5)
                .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        localDataSource.setFavoriteMovie(movie, state)
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(5)
                .build()

        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        localDataSource.setFavoriteTvShow(tvShow, state)
    }

    override fun deleteFavoriteMovie(movie: MovieEntity) {
        val movie : MovieEntity = movie
        movie.isFav = true
        localDataSource.deleteFavoriteMovie(movie)
    }

    override fun deleteFavoriteTvShow(tvShow: TvShowEntity) {
        val tvShow: TvShowEntity = tvShow
        tvShow.isFav = true
        localDataSource.deleteFavoriteTvShow(tvShow)
    }
}