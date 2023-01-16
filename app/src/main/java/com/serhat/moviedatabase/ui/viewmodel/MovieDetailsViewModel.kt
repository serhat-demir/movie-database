package com.serhat.moviedatabase.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhat.moviedatabase.data.model.movie.Movie
import com.serhat.moviedatabase.data.model.movie.MovieSummary
import com.serhat.moviedatabase.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val mRepo: MovieRepository): ViewModel() {
    var message: MutableLiveData<String> = mRepo.message

    val similarMovies: MutableLiveData<List<MovieSummary>> = mRepo.movies
    val currentPage: MutableLiveData<Int> = mRepo.currentPage
    val totalPages: MutableLiveData<Int> = mRepo.totalPages

    val movie: MutableLiveData<Movie> = mRepo.movie

    init {
        currentPage.value = 1
    }

    fun getMovieDetails(movie_id: Int, language: String) {
        mRepo.getMovieDetails(movie_id, language)
    }

    fun getSimilarMovies(movie_id: Int, language: String, page: Int) {
        mRepo.getSimilarMovies(movie_id, language, page)
    }
}