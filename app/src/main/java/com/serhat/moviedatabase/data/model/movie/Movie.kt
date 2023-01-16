package com.serhat.moviedatabase.data.model.movie

import com.google.gson.annotations.SerializedName
import com.serhat.moviedatabase.data.model.Company
import com.serhat.moviedatabase.data.model.Country
import com.serhat.moviedatabase.data.model.Genre

data class Movie(
    @SerializedName("adult")
    var adult: Boolean,

    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("belongs_to_collection")
    var belongs_to_collection: MovieCollection?,

    @SerializedName("budget")
    var budget: Int,

    @SerializedName("genres")
    var genres: List<Genre>,

    @SerializedName("homepage")
    var homepage: String?,

    @SerializedName("id")
    var id: Int,

    @SerializedName("imdb_id")
    var imdb_id: String?,

    @SerializedName("original_language")
    var original_language: String,

    @SerializedName("original_title")
    var original_title: String,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("production_companies")
    var production_companies: List<Company>,

    @SerializedName("production_countries")
    var production_countries: List<Country>,

    @SerializedName("release_date")
    var release_date: String,

    @SerializedName("revenue")
    var revenue: Int,

    @SerializedName("runtime")
    var runtime: Int?,

    @SerializedName("spoken_languages")
    var spoken_languages: List<MovieLanguage>,

    @SerializedName("status")
    var status: String,

    @SerializedName("tagline")
    var tagline: String?,

    @SerializedName("title")
    var title: String,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Double,

    @SerializedName("vote_count")
    var vote_count: Int
)