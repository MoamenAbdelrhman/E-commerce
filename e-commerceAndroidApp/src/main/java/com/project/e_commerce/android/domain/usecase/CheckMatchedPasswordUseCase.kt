package com.project.e_commerce.android.domain.usecase

class CheckMatchedPasswordUseCase {

    operator fun invoke(newPassword: String, confirmPassword: String) =
        !newPassword.isNullOrEmpty() && newPassword == confirmPassword
}