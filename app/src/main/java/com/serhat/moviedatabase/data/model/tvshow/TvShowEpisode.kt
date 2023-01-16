package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowEpisode(
    @SerializedName("air_date")
    var air_date: String,

    @SerializedName("episode_number")
    var episode_number: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("production_code")
    var production_code: String,

    @SerializedName("runtime")
    var runtime: Int,

    @SerializedName("season_number")
    var season_number: Int,

    @SerializedName("show_id")
    var show_id: Int,

    @SerializedName("still_path")
    var still_path: String?,

    @SerializedName("vote_average")
    var vote_average: Double,

    @SerializedName("vote_count")
    var vote_count: Int
)