package com.project.e_commerce.android.data.remote.response

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String,

    @SerializedName("translations")
    val translations: Map<String, Translation>
)
