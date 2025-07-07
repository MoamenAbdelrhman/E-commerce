package com.project.e_commerce.android.presentation.ui.screens.loginScreen

data class LoginUIState(
    val email: EmailField = EmailField(),
    val password: PasswordField = PasswordField(),
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

data class EmailField(
    val email: String = "",
    val errorMessage: String = "",
    val isError: Boolean = false,
)
data class PasswordField(
    val password: String = "",
    val errorMessage: String = "",
    val isError: Boolean = false
)