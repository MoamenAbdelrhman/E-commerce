package com.project.e_commerce.android

import android.app.Application

import com.project.e_commerce.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class EcommerceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EcommerceApp)
            modules(
                listOf(
                    viewModelModule,
                )
            )
        }
    }
}