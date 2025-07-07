package com.project.e_commerce.android.domain.usecase

import com.project.e_commerce.android.data.repository.AuthRepository

class SignOutUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.signOut()
}