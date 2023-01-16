package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowLanguage(
    @SerializedName("english_name")
    var english_name: String,

    @SerializedName("iso_639_1")
    var name_short: String,

    @SerializedName("name")
    var name: String
)