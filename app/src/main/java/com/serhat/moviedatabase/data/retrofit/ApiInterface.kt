package com.serhat.moviedatabase.data.retrofit

import com.serhat.moviedatabase.data.model.movie.Movie
import com.serhat.moviedatabase.data.model.movie.MovieList
import com.serhat.moviedatabase.data.model.tvshow.TvShow
import com.serhat.moviedatabase.data.model.tvshow.TvShowList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    // Movie Repository
    @GET("search/movie")
    fun searchMovies(
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Single<MovieList>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieList>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieList>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieList>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("language") language: String
    ): Single<Movie>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movie_id: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieList>

    // TvShow Repository
    @GET("search/tv")
    fun searchTvShows(
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Single<TvShowList>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowList>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowList>

    @GET("tv/on_the_air")
    fun getTvShowsOnTheAir(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowList>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(
        @Path("tv_id") tv_id: Int,
        @Query("language") language: String
    ): Single<TvShow>

    @GET("tv/{tv_id}/similar")
    fun getSimilarTvShows(
        @Path("tv_id") tv_id: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowList>
}