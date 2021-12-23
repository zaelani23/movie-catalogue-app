package com.zaelani.moviecatalogue.data.source


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.zaelani.moviecatalogue.data.source.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.zaelani.moviecatalogue.data.source.local.LocalDataSource
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.utils.AppExecutors
import com.zaelani.moviecatalogue.utils.DataDummy
import com.zaelani.moviecatalogue.utils.LiveDataTestUtil
import com.zaelani.moviecatalogue.utils.PagedListUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.getRemoteMovies()
    private val tvShowResponse = DataDummy.getRemoteTvShows()

    private val movieDummy = DataDummy.getMovies()[0]
    private val tvShowDummy = DataDummy.getTvShows()[0]

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(tvShowResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun getMovie(){
        val movie = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovie(movieDummy))
        assertNotNull(movie)
        assertEquals(movieDummy,movie)
    }

    @Test
    fun getTvShow(){
        val tvShow = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShow(tvShowDummy))
        assertNotNull(tvShow)
        assertEquals(tvShowDummy, tvShow)
    }

    @Test
    fun setFavoriteMovie(){
        movieCatalogueRepository.setFavoriteMovie(movieDummy, true)
        verify(local).setFavoriteMovie(movieDummy, true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setFavoriteTvShow(){
        movieCatalogueRepository.setFavoriteTvShow(tvShowDummy, true)
        verify(local).setFavoriteTvShow(tvShowDummy, true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoriteMovies()

        val movieEntities = PagedListUtil.mockPagedList(DataDummy.getMovies())
        verify(local).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavTvShows()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoriteTvShows()

        val tvShowEntities = PagedListUtil.mockPagedList(DataDummy.getTvShows())
        verify(local).getFavTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }
}