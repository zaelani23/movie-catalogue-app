package com.zaelani.moviecatalogue.ui.detail.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataDummy.getMovies()[0]
        val movieLiveData = MutableLiveData<MovieEntity>()
        movieLiveData.value = dummyMovie

        `when`(movieCatalogueRepository.getMovie(dummyMovie)).thenReturn(movieLiveData)
        viewModel.setSelectedMovie(dummyMovie)
        val movie = viewModel.getMovie()
        verify(movieCatalogueRepository).getMovie(dummyMovie)
        assertNotNull(movie)
        assertEquals(movieLiveData, movie)
    }

    @Test
    fun setDeleteFavoriteMovie(){
        val dummyMovie = DataDummy.getMovies()[0]
        viewModel.setSelectedMovie(dummyMovie)
        viewModel.setFavoriteMovie(dummyMovie)
        viewModel.deleteFavoriteMovie(dummyMovie)
        verifyNoMoreInteractions(observer)
    }
}