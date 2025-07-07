package com.project.e_commerce.android.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()



        val newRequest = originalRequest.newBuilder()
            .build()

        return chain.proceed(newRequest)
    }
}
