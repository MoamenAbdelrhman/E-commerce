package com.project.e_commerce.android.domain.repository

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.project.e_commerce.android.data.repository.AuthRepository
import com.project.e_commerce.android.presentation.ui.utail.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val authFireBase: FirebaseAuth
) : AuthRepository {
    override suspend fun loginByNameAndPassword(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow{
            emit(Resource.Loading())
            val result = authFireBase.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
            Log.i("Firebase Authentication", result.toString())

        }.catch {
            emit(Resource.Error(message = it.message.toString()))
            Log.i("Firebase Authentication", it.message.toString())
        }
    }

    override suspend fun createAccount(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow{
            emit(Resource.Loading())
            val result = authFireBase.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }    }

    override fun signOut() {
        authFireBase.signOut()
    }

    override fun getCurrentUser() = authFireBase.currentUser
    override suspend fun sendEmailToSendTheVerifiedCode(email: String): Resource<Resource<Unit>> {
        TODO("Not yet implemented")
    }

}