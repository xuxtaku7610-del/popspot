package com.popspot.app.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser?>
    fun getCurrentUser(): FirebaseUser?
    fun signOut()
}