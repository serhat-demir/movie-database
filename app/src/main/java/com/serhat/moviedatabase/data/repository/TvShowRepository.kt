package com.serhat.moviedatabase.data.repository

import androidx.lifecycle.MutableLiveData
import com.serhat.moviedatabase.data.model.tvshow.TvShow
import com.serhat.moviedatabase.data.model.tvshow.TvShowList
import com.serhat.moviedatabase.data.model.tvshow.TvShowSummary
import com.serhat.moviedatabase.data.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TvShowRepository(private val apiService: ApiInterface) {
    private val disposable = CompositeDisposable()
    val message = MutableLiveData<String>()

    val tvShows = MutableLiveData<List<TvShowSummary>>()
    val currentPage = MutableLiveData<Int>()
    val totalPages = MutableLiveData<Int>()

    val tvShow = MutableLiveData<TvShow>()

    fun searchTvShows(language: String, query: String, page: Int) {
        disposable.add(
            apiService.searchTvShows(language, query, page, false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TvShowList>() {
                    override fun onSuccess(t: TvShowList) {
                        tvShows.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getPopularTvShows(language: String, page: Int) {
        disposable.add(
            apiService.getPopularTvShows(language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TvShowList>() {
                    override fun onSuccess(t: TvShowList) {
                        tvShows.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getTopRatedTvShows(language: String, page: Int) {
        disposable.add(
            apiService.getTopRatedTvShows(language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TvShowList>() {
                    override fun onSuccess(t: TvShowList) {
                        tvShows.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getTvShowsOnTheAir(language: String, page: Int) {
        disposable.add(
            apiService.getTvShowsOnTheAir(language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TvShowList>() {
                    override fun onSuccess(t: TvShowList) {
                        tvShows.value = t.results
                        currentPage.value = t.page
                        totalPages.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getTvShowDetails(tv_id: Int, language: String) {
        disposable.add(
            apiService.getTvShowDetails(tv_id, language)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TvShow>() {
                    override fun onSuccess(t: TvShow) {
                        tvShow.value = t
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
        )
    }

    fun getSimilarTvShows(tv_id: Int, language: String, page: Int) {
        disposable.add(
            apiService.getSimilarTvShows(tv_id, language, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TvShowList>() {
                    override fun onSuccess(t: TvShowList) {
                        tvShows.value = t.results
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