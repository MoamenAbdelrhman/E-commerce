package com.project.e_commerce.android.data.remote.response

import com.google.gson.annotations.SerializedName


data class Flags(
    @SerializedName("png")
    val png: String,

    @SerializedName("svg")
    val svg: String
)