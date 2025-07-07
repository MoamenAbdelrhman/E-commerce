package com.project.e_commerce.android.presentation.viewModel.profileViewModel

import androidx.navigation.NavController

interface ProfileScreenInteraction {

    fun onClickProfileOption(navController: NavController)

    fun onClickSettingsOption(navController: NavController)

    fun onClickRequestHelpOption(navController: NavController)

    fun onClickLogoutOption(navController: NavController)

}