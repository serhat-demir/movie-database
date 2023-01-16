package com.serhat.moviedatabase.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhat.moviedatabase.data.model.movie.MovieSummary
import com.serhat.moviedatabase.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val mRepo: MovieRepository): ViewModel() {
    var message: MutableLiveData<String> = mRepo.message

    val movies: MutableLiveData<List<MovieSummary>> = mRepo.movies
    val currentPage: MutableLiveData<Int> = mRepo.currentPage
    val totalPages: MutableLiveData<Int> = mRepo.totalPages

    init {
        currentPage.value = 1
    }

    fun searchMovies(language: String, query: String, page: Int) {
        mRepo.searchMovies(language, query, page)
    }

    fun getPopularMovies(language: String, page: Int) {
        mRepo.getPopularMovies(language, page)
    }

    fun getTopRatedMovies(language: String, page: Int) {
        mRepo.getTopRatedMovies(language, page)
    }

    fun getUpcomingMovies(language: String, page: Int) {
        mRepo.getUpcomingMovies(language, page)
    }
}