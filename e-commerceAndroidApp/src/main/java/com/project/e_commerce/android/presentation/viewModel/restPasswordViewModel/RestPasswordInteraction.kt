package com.project.e_commerce.android.presentation.viewModel.restPasswordViewModel

import androidx.navigation.NavController

interface RestPasswordInteraction {

    fun onWriteNewPassword(newPassword: String)

    fun onWriteNewPasswordConfirmed(newPassword: String)

    fun onClickChangePassword(navController: NavController)

    fun onClickBackArrowButton(navController: NavController)
}