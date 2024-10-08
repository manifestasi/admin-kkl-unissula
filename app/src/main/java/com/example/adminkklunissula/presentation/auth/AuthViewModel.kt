package com.example.adminkklunissula.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adminkklunissula.data.repository.AuthRepository
import com.example.adminkklunissula.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _loginResult = MutableLiveData<Resource<FirebaseUser?>>()
    val loginResult: LiveData<Resource<FirebaseUser?>> = _loginResult

    fun loginUser(nim: String, password: String) {
        _loginResult.value = Resource.Loading
        authRepository.loginUser(nim, password).observeForever { result ->
            _loginResult.value = result
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }

    private val resultLogout = MediatorLiveData<Boolean>()

    fun logoutUser(): LiveData<Boolean> {
        val loggedOut = authRepository.logoutUser()
        if(loggedOut){
            resultLogout.value = true
        } else {
            resultLogout.value = false
        }

        return resultLogout
    }
}