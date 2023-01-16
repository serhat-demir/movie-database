package com.serhat.moviedatabase.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhat.moviedatabase.data.model.tvshow.TvShow
import com.serhat.moviedatabase.data.model.tvshow.TvShowSummary
import com.serhat.moviedatabase.data.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(private val tvRepo: TvShowRepository): ViewModel() {
    val message: MutableLiveData<String> = tvRepo.message

    val tvShows: MutableLiveData<List<TvShowSummary>> = tvRepo.tvShows
    val currentPage: MutableLiveData<Int> = tvRepo.currentPage
    val totalPages: MutableLiveData<Int> = tvRepo.totalPages

    init {
        currentPage.value = 1
    }

    fun searchTvShows(language: String, query: String, page: Int) {
        tvRepo.searchTvShows(language, query, page)
    }

    fun getPopularTvShows(language: String, page: Int) {
        tvRepo.getPopularTvShows(language, page)
    }

    fun getTopRatedTvShows(language: String, page: Int) {
        tvRepo.getTopRatedTvShows(language, page)
    }

    fun getTvShowsOnTheAir(language: String, page: Int) {
        tvRepo.getTvShowsOnTheAir(language, page)
    }
}