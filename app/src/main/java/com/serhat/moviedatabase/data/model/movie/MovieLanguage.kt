package com.serhat.moviedatabase.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieLanguage(
    @SerializedName("iso_639_1")
    var name_short: String,

    @SerializedName("name")
    var name: String
)