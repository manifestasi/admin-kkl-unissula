package com.example.adminkklunissula.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.adminkklunissula.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
)
{
    fun loginUser(nim: String, password: String): LiveData<Resource<FirebaseUser?>> {
        val resultLiveData = MutableLiveData<Resource<FirebaseUser?>>()
        resultLiveData.value = Resource.Loading

        firebaseAuth.signInWithEmailAndPassword("$nim@myuniversity.com", password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resultLiveData.value = Resource.Success(firebaseAuth.currentUser)
                } else {
                    resultLiveData.value = Resource.Error(task.exception ?: Exception("Unknown error"))
                }
            }

        return resultLiveData
    }

    fun logoutUser(): Boolean {
        firebaseAuth.signOut()
        return firebaseAuth.currentUser == null
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

}