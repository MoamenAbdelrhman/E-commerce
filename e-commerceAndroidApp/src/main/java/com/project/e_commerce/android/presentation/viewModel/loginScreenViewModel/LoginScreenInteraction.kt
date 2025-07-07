package com.project.e_commerce.android.presentation.viewModel.loginScreenViewModel

import android.content.Context
import androidx.navigation.NavController

interface LoginScreenInteraction {

    fun onUserWriteEmail(email: String)

    fun onUserWritePassword(password: String)

    fun onClickLogin(navController: NavController, context: Context)

    fun onClickRestPassword(navController: NavController)

    fun onClickLoginByGoogle(navController: NavController)

    fun onClickLoginByFacebook(navController: NavController)

    fun onClickLoginByApple(navController: NavController)

    fun onClickNotHaveAnAccountCreateNewAccount(navController: NavController)
}