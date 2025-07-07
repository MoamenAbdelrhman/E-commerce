package com.project.e_commerce.android.presentation.viewModel.restPasswordViewModel

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.e_commerce.android.domain.usecase.CheckMatchedPasswordUseCase
import com.project.e_commerce.android.domain.usecase.CheckPasswordValidation
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.changePasswordScreen.ChangePasswordUIState
import com.project.e_commerce.android.presentation.viewModel.baseViewModel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestPasswordViewModel(
    private val checkPasswordValidation: CheckPasswordValidation,
    private val checkMatchedPasswordUseCase : CheckMatchedPasswordUseCase
) : RestPasswordInteraction, BaseViewModel() {

    private val _state = MutableStateFlow(ChangePasswordUIState())
    val state : StateFlow<ChangePasswordUIState> get() = _state

    override fun onWriteNewPassword(newPassword: String) {
        val validation = checkPasswordValidation(newPassword)
        val copyState = _state.value.copy(
            newPassword = _state.value.newPassword.copy(
                value = newPassword,
                isError = validation.first,
                errorMessage = validation.second
            )
        )
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onWriteNewPasswordConfirmed(newPassword: String) {
        val validation = checkPasswordValidation(newPassword)
        val copyState = _state.value.copy(
            confirmNewPassword = _state.value.confirmNewPassword.copy(
                value = newPassword,
                isError = validation.first,
                errorMessage = validation.second
            )
        )
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onClickChangePassword(navController: NavController) {
        setLoadingState(true)
        val newPassword = _state.value.newPassword.value
        val confirmNewPassword = _state.value.confirmNewPassword.value
            if(checkMatchedPasswordUseCase.invoke(newPassword, confirmNewPassword)){
                setLoadingState(false)
                setErrorState(false, "")
                val copyState = _state.value.copy(
                    isSuccessChanged = true
                )
                viewModelScope.launch {
                    _state.emit(copyState)
                    delay(2000)
                    navController.navigate(Screens.LoginScreen.route)
                }
            }
            else {
                setLoadingState(false)
                setErrorState(true, "كلمة المرور غير مطابقة")
            }
    }

    override fun onClickBackArrowButton(navController: NavController) {
        navController.popBackStack()
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