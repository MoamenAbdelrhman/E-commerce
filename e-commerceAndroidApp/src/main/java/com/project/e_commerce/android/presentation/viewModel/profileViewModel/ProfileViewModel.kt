package com.project.e_commerce.android.presentation.viewModel.profileViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.project.e_commerce.android.presentation.ui.navigation.Screens

class ProfileViewModel : ViewModel(), ProfileScreenInteraction {
    override fun onClickProfileOption(navController: NavController) {
        navController.navigate(Screens.ProfileScreen.EditPersonalProfile.route)
    }

    override fun onClickSettingsOption(navController: NavController) {
        navController.navigate(Screens.ProfileScreen.SettingsScreen.route)
    }

    override fun onClickRequestHelpOption(navController: NavController) {
        navController.navigate(Screens.ProfileScreen.RequestHelpScreen.route)
    }

    override fun onClickLogoutOption(navController: NavController) {
        navController.navigate(Screens.LoginScreen.route) {
            popUpTo(Screens.ProfileScreen.route) {
                inclusive = true
            }
        }
    }
}