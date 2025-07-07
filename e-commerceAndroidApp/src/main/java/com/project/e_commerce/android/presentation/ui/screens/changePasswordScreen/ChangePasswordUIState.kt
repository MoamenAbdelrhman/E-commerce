package com.project.e_commerce.android.presentation.ui.screens.changePasswordScreen

import com.project.e_commerce.android.data.model.FieldState


data class ChangePasswordUIState(
    val newPassword: FieldState = FieldState(),
    val confirmNewPassword: FieldState = FieldState(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccessChanged: Boolean = false,
    val errorMessage: String = "",
)
