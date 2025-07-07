package com.project.e_commerce.android.data.remote.response

import com.google.gson.annotations.SerializedName


data class CountryResponse(
    @SerializedName("name")
    val name: Name,
    @SerializedName("flags")
    val flags: Flags
)