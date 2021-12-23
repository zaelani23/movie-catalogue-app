package com.zaelani.moviecatalogue.di

import android.content.Context
import com.zaelani.moviecatalogue.data.source.MovieCatalogueRepository
import com.zaelani.moviecatalogue.data.source.local.LocalDataSource
import com.zaelani.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.zaelani.moviecatalogue.data.source.remote.RemoteDataSource
import com.zaelani.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val database = MovieCatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieCatalogueDao())
        val appExecutors = AppExecutors()
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}