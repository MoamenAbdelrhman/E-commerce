package com.project.e_commerce.android.data.remote.api

import com.project.e_commerce.android.data.remote.response.CountryResponse
import retrofit2.http.GET

interface CountriesApi {

    @GET("v3.1/all")
    suspend fun getCountry() :  List<CountryResponse>

}
