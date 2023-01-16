package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowNetwork(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("logo_path")
    var logo_path: String?,

    @SerializedName("origin_country")
    var origin_country: String
)