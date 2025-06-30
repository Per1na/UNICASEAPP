package com.example.unicase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unicase.repository.AuthRepository
import com.example.unicase.repository.LoginRequest
import com.example.unicase.repository.LoginResponse
import com.example.unicase.repository.RegisterRequest
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    var loginState by mutableStateOf<Result<LoginResponse>?>(null)
    var registerState by mutableStateOf<Result<LoginResponse>?>(null)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequest(email, password))
                if (response.isSuccessful && response.body() != null) {
                    loginState = Result.success(response.body()!!)
                } else {
                    loginState = Result.failure(Exception("Login failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                loginState = Result.failure(e)
            }
        }
    }

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            try {
                val response = repository.register(RegisterRequest(name, email, password, confirmPassword))
                if (response.isSuccessful && response.body() != null) {
                    registerState = Result.success(response.body()!!)
                } else {
                    registerState = Result.failure(Exception("Register failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                registerState = Result.failure(e)
            }
        }
    }
}
