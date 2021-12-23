package com.zaelani.moviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
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
class FavoriteTvShowViewModelTest{
    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavTvShows(){
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(2)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(movieCatalogueRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShows().value
        verify(movieCatalogueRepository).getFavoriteTvShows()
        assertNotNull(tvShow)
        assertEquals(2, tvShow?.size)
        viewModel.getFavTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}