package com.project.e_commerce.android.data.repository


import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.project.e_commerce.android.presentation.ui.utail.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun loginByNameAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun createAccount(email: String, password: String): Flow<Resource<AuthResult>>

    fun signOut()

    fun getCurrentUser(): FirebaseUser?

    suspend fun sendEmailToSendTheVerifiedCode(email: String) : Resource<Resource<Unit>>
}
