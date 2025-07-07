package com.project.e_commerce.android.domain.usecase

import com.project.e_commerce.android.data.repository.AuthRepository

class SendEmailToSendTheVerifiedCode(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) =
        authRepository.sendEmailToSendTheVerifiedCode(email)

}