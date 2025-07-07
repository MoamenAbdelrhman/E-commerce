package com.project.e_commerce.android.data.repository

import com.project.e_commerce.android.data.remote.api.CountriesApi
import com.project.e_commerce.android.data.remote.response.CountryResponse

class CountriesRepository(private val apiService: CountriesApi) {

    suspend fun getAllCountries(): List<CountryResponse> {
        return apiService.getCountry()
    }
}