package com.serhat.moviedatabase.data.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("iso_3166_1")
    var name_short: String,

    @SerializedName("name")
    var name: String
)