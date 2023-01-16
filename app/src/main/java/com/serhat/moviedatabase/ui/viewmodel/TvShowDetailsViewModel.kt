package com.serhat.moviedatabase.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhat.moviedatabase.data.model.tvshow.TvShow
import com.serhat.moviedatabase.data.model.tvshow.TvShowSummary
import com.serhat.moviedatabase.data.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(private val tvRepo: TvShowRepository): ViewModel() {
    val message: MutableLiveData<String> = tvRepo.message

    val similarTvShows: MutableLiveData<List<TvShowSummary>> = tvRepo.tvShows
    val currentPage: MutableLiveData<Int> = tvRepo.currentPage
    val totalPages: MutableLiveData<Int> = tvRepo.totalPages

    val tvShow: MutableLiveData<TvShow> = tvRepo.tvShow

    init {
        currentPage.value = 1
    }

    fun getTvShowDetails(tv_id: Int, language: String) {
        tvRepo.getTvShowDetails(tv_id, language)
    }

    fun getSimilarTvShows(tv_id: Int, language: String, page: Int) {
        tvRepo.getSimilarTvShows(tv_id, language, page)
    }
}