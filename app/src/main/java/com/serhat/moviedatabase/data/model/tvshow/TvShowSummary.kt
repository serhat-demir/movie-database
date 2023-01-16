package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowSummary(
    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("id")
    var id: Int,

    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("vote_average")
    var vote_average: Double,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("first_air_date")
    var first_air_date: String,

    @SerializedName("origin_country")
    var origin_country: List<String>,

    @SerializedName("genre_ids")
    var genre_ids: List<Int>,

    @SerializedName("original_language")
    var original_language: String,

    @SerializedName("vote_count")
    var vote_count: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("original_name")
    var original_name: String
)