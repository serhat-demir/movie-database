package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowSeason(
    @SerializedName("air_date")
    var air_date: String,

    @SerializedName("episode_count")
    var episode_count: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("season_number")
    var season_number: Int
)