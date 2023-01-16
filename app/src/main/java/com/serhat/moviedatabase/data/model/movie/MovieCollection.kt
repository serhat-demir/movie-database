package com.serhat.moviedatabase.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieCollection(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("backdrop_path")
    var backdrop_path: String,
)