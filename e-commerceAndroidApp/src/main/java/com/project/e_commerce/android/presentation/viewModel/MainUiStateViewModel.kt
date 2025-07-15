package com.project.e_commerce.android.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainUiStateViewModel : ViewModel() {
    private val _isBottomSheetVisible = MutableStateFlow(false)
    val isBottomSheetVisible: StateFlow<Boolean> = _isBottomSheetVisible

    private val _isAddToCartSheetVisible = MutableStateFlow(false)
    val isAddToCartSheetVisible: StateFlow<Boolean> = _isAddToCartSheetVisible

    fun showAddToCartSheet() { _isAddToCartSheetVisible.value = true }
    fun hideAddToCartSheet() { _isAddToCartSheetVisible.value = false }

    fun setBottomSheetVisible(visible: Boolean) {
        _isBottomSheetVisible.value = visible
    }

}