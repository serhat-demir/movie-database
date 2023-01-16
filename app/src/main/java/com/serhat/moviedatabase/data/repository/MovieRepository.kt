package com.serhat.moviedatabase.data.repository

import androidx.lifecycle.MutableLiveData
import com.serhat.moviedatabase.data.model.movie.Movie
import com.serhat.moviedatabase.data.model.movie.MovieList
import com.serhat.moviedatabase.data.model.movie.MovieSummary
import com.serhat.moviedatabase.data.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieRepository(private val apiService: ApiInterface) {
    private val disposable = CompositeDisposable()
    val message = MutableLiveData<String>()

    val movies = MutableLiveData<List<MovieSummary>>()
    val currentPage = MutableLiveData<Int>()
    val totalPages = MutableLiveData<Int>()

    val movie = MutableLiveData<Movie>()

    fun searchMovies(language: String, query: String, page: Int) {
        disposable.add(
            apiService.searchMovies(language, query, page, false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieList>() {
                    override fun onSuccess(t: MovieList) {
                        movies.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getPopularMovies(language: String, page: Int) {
        disposable.add(
            apiService.getPopularMovies(language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieList>() {
                    override fun onSuccess(t: MovieList) {
                        movies.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getTopRatedMovies(language: String, page: Int) {
        disposable.add(
            apiService.getTopRatedMovies(language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieList>() {
                    override fun onSuccess(t: MovieList) {
                        movies.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getUpcomingMovies(language: String, page: Int) {
        disposable.add(
            apiService.getUpcomingMovies(language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieList>() {
                    override fun onSuccess(t: MovieList) {
                        movies.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getMovieDetails(movie_id: Int, language: String) {
        disposable.add(
            apiService.getMovieDetails(movie_id, language)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Movie>() {
                    override fun onSuccess(t: Movie) {
                        movie.value = t
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getSimilarMovies(movie_id: Int, language: String, page: Int) {
        disposable.add(
            apiService.getSimilarMovies(movie_id, language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieList>() {
                    override fun onSuccess(t: MovieList) {
                        movies.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }
}