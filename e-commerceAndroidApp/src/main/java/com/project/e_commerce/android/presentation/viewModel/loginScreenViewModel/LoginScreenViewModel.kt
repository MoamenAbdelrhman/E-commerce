package com.project.e_commerce.android.presentation.viewModel.loginScreenViewModel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.e_commerce.android.domain.usecase.CheckEmailValidation
import com.project.e_commerce.android.domain.usecase.CheckLoginValidation
import com.project.e_commerce.android.domain.usecase.CheckPasswordValidation
import com.project.e_commerce.android.domain.usecase.LoginByEmailAndPasswordUseCase
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.loginScreen.EmailField
import com.project.e_commerce.android.presentation.ui.screens.loginScreen.LoginUIState
import com.project.e_commerce.android.presentation.ui.screens.loginScreen.PasswordField
import com.project.e_commerce.android.presentation.ui.utail.Resource
import com.project.e_commerce.android.presentation.viewModel.baseViewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val checkLoginValidation: CheckLoginValidation,
    private val checkEmailValidation: CheckEmailValidation,
    private val checkPasswordValidation: CheckPasswordValidation,
    private val loginByEmailAndPasswordUseCase: LoginByEmailAndPasswordUseCase
) : BaseViewModel(), LoginScreenInteraction {

    private val _state = MutableStateFlow(LoginUIState())
    val state: StateFlow<LoginUIState> get() = _state

    override fun onUserWriteEmail(email: String) {
        val validation = checkEmailValidation.invoke(email)
        val copyState = _state.value.copy(
            email = _state.value.email.copy(
                email = email,
                isError = validation.first,
                errorMessage = validation.second
            )
        )
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onUserWritePassword(password: String) {
        val validation = checkPasswordValidation(password)
        val copyState = _state.value.copy(
            password = _state.value.password.copy(
                password = password,
                isError = validation.first,
                errorMessage = validation.second
            )
        )
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onClickLogin(navController: NavController, context: Context) {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("isLoggedIn", true).apply()

        navController.navigate(Screens.ReelsScreen.route) {
            popUpTo(Screens.LoginScreen.route) { inclusive = true }
        }
    }

    override fun onClickRestPassword(navController: NavController) {
        navController.navigate(Screens.LoginScreen.EnterEmailScreen.route)
    }

    override fun onClickLoginByGoogle(navController: NavController) {
        TODO("Not yet implemented")
    }

    override fun onClickLoginByFacebook(navController: NavController) {
        TODO("Not yet implemented")
    }

    override fun onClickLoginByApple(navController: NavController) {
        TODO("Not yet implemented")
    }

    override fun onClickNotHaveAnAccountCreateNewAccount(navController: NavController) {
        navController.navigate(Screens.LoginScreen.CreateAccountScreen.route)
    }

    override fun setLoadingState(loadingState: Boolean) {
        val copyState = _state.value.copy(
            isLoading = loadingState
        )
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }


    override fun setErrorState(errorState: Boolean, errorMessage: String) {
        val copyState = _state.value.copy(
            isError = errorState,
            errorMessage = errorMessage
        )
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

}