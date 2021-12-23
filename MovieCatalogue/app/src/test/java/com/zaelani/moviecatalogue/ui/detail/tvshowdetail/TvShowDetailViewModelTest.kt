package com.zaelani.moviecatalogue.ui.detail.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
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
class TvShowDetailViewModelTest {
    private lateinit var viewModel: TvShowDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(movieCatalogueRepository)
    }


    @Test
    fun getTvShow(){
        val dummyTvShow = DataDummy.getTvShows()[0]
        val tvShowLiveData = MutableLiveData<TvShowEntity>()
        tvShowLiveData.value = dummyTvShow

        `when`(movieCatalogueRepository.getTvShow(dummyTvShow)).thenReturn(tvShowLiveData)
        viewModel.setSelectedTvShow(dummyTvShow)
        val tvShow = viewModel.getTvShow()
        verify(movieCatalogueRepository).getTvShow(dummyTvShow)
        assertNotNull(tvShow)
        assertEquals(tvShowLiveData, tvShow)
    }

    @Test
    fun setDeleteFavoriteMovie(){
        val dummyTvShow = DataDummy.getTvShows()[0]
        viewModel.setSelectedTvShow(dummyTvShow)
        viewModel.setFavoriteTvShow(dummyTvShow)
        viewModel.deleteFavoriteTvShow(dummyTvShow)
        verifyNoMoreInteractions(observer)
    }
}