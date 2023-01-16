package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowCreator(
    @SerializedName("id")
    var id: Int,

    @SerializedName("credit_id")
    var credit_id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("gender")
    var gender: Int,

    @SerializedName("profile_path")
    var profile_path: String?
)