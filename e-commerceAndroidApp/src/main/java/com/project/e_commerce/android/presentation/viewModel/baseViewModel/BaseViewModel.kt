package com.project.e_commerce.android.presentation.viewModel.baseViewModel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun setLoadingState(loadingState: Boolean)

    abstract fun setErrorState(errorState: Boolean, errorMessage: String = "")
}