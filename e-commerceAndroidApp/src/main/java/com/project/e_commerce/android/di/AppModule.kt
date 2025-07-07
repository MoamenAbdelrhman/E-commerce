package com.project.e_commerce.android.di

import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.e_commerce.android.data.remote.api.CountriesApi
import com.project.e_commerce.android.data.remote.interceptor.Interceptor
import com.project.e_commerce.android.data.repository.AuthRepository
import com.project.e_commerce.android.data.repository.CountriesRepository
import com.project.e_commerce.android.domain.repository.AuthRepositoryImpl
import com.project.e_commerce.android.domain.usecase.CheckEmailValidation
import com.project.e_commerce.android.domain.usecase.CheckLoginValidation
import com.project.e_commerce.android.domain.usecase.CheckMatchedPasswordUseCase
import com.project.e_commerce.android.domain.usecase.CheckPasswordValidation
import com.project.e_commerce.android.domain.usecase.CreateNewAccountUseCase
import com.project.e_commerce.android.domain.usecase.GetCurrentUserFirebaseUseCase
import com.project.e_commerce.android.domain.usecase.LoginByEmailAndPasswordUseCase
import com.project.e_commerce.android.domain.usecase.SendEmailToSendTheVerifiedCode
import com.project.e_commerce.android.domain.usecase.SignOutUseCase
import com.project.e_commerce.android.presentation.viewModel.loginScreenViewModel.LoginScreenViewModel
import com.project.e_commerce.android.presentation.viewModel.profileViewModel.ProfileViewModel
import com.project.e_commerce.android.presentation.viewModel.reelsScreenViewModel.ReelsScreenViewModel
import com.project.e_commerce.android.presentation.viewModel.restPasswordViewModel.RestPasswordViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    viewModel {
        LoginScreenViewModel(
            checkLoginValidation = get(),
            checkEmailValidation = get(),
            checkPasswordValidation = get(),
            loginByEmailAndPasswordUseCase = get()
        )
    }



    viewModel { ProfileViewModel() }




    viewModel{ RestPasswordViewModel(checkPasswordValidation = get(), checkMatchedPasswordUseCase =  get()) }

    viewModel { ReelsScreenViewModel()}

    single {

         val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor())
            .build()

        Retrofit.Builder()
            .client(okHttpClient.newBuilder().build())
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(CountriesApi::class.java) }

    single { CountriesRepository(get()) }


    single { CheckMatchedPasswordUseCase() }

    single<AuthRepository> { AuthRepositoryImpl(authFireBase = get()) }

    single { CreateNewAccountUseCase(authRepository = get()) }

    single { GetCurrentUserFirebaseUseCase(authRepository = get()) }

    single { LoginByEmailAndPasswordUseCase(authRepository = get()) }

    single { SignOutUseCase(authRepository = get()) }

    single { SendEmailToSendTheVerifiedCode(authRepository = get()) }

    single { CheckLoginValidation() }

    single { FirebaseAuth.getInstance() }

    single { CheckEmailValidation() }

    single { CheckPasswordValidation() }


}


