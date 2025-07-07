package com.project.e_commerce.android.data.remote.response

import com.google.gson.annotations.SerializedName


data class Translation(
    @SerializedName("official")
    val official: String,

    @SerializedName("common")
    val common: String
)