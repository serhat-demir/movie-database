package com.serhat.moviedatabase.di

import com.serhat.moviedatabase.data.repository.MovieRepository
import com.serhat.moviedatabase.data.repository.TvShowRepository
import com.serhat.moviedatabase.data.retrofit.ApiClient
import com.serhat.moviedatabase.data.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTvShowRepository(apiService: ApiInterface): TvShowRepository = TvShowRepository(apiService)

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiInterface): MovieRepository = MovieRepository(apiService)

    @Provides
    @Singleton
    fun provideApiService(): ApiInterface = ApiClient.getClient()
}