package com.serhat.moviedatabase.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("page")
    var page: Int,

    @SerializedName("total_pages")
    var total_pages: Int,

    @SerializedName("total_results")
    var total_results: Int,

    @SerializedName("results")
    var results: List<TvShowSummary>
)