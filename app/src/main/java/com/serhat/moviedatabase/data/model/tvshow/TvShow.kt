package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName
import com.serhat.moviedatabase.data.model.Company
import com.serhat.moviedatabase.data.model.Country
import com.serhat.moviedatabase.data.model.Genre

data class TvShow(
    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("created_by")
    var created_by: List<TvShowCreator>,

    @SerializedName("episode_run_time")
    var episode_run_time: List<Int>,

    @SerializedName("first_air_date")
    var first_air_date: String,

    @SerializedName("genres")
    var genres: List<Genre>,

    @SerializedName("homepage")
    var homepage: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("in_production")
    var in_production: Boolean,

    @SerializedName("languages")
    var languages: List<String>,

    @SerializedName("last_air_date")
    var last_air_date: String,

    @SerializedName("last_episode_to_air")
    var last_episode_to_air: TvShowEpisode,

    @SerializedName("name")
    var name: String,

    @SerializedName("next_episode_to_air")
    var next_episode_to_air: TvShowEpisode?,

    @SerializedName("networks")
    var networks: List<TvShowNetwork>,

    @SerializedName("number_of_episodes")
    var number_of_episodes: Int,

    @SerializedName("number_of_seasons")
    var number_of_seasons: Int,

    @SerializedName("origin_country")
    var origin_country: List<String>,

    @SerializedName("original_language")
    var original_language: String,

    @SerializedName("original_name")
    var original_name: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("production_companies")
    var production_companies: List<Company>,

    @SerializedName("production_countries")
    var production_countries: List<Country>,

    @SerializedName("seasons")
    var seasons: List<TvShowSeason>,

    @SerializedName("spoken_languages")
    var spoken_languages: List<TvShowLanguage>,

    @SerializedName("status")
    var status: String,

    @SerializedName("tagline")
    var tagline: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("vote_average")
    var vote_average: Double,

    @SerializedName("vote_count")
    var vote_count: Int
)