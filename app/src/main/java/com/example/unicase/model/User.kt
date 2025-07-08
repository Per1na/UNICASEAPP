package com.example.unicase.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Data class untuk menampung semua info user
data class User(
    val name: String,
    val userId: String,
    val email: String,
    val phoneNumber: String,
    val gender: String,
    val dateOfBirth: String
)

// State global untuk data pengguna dengan nilai awal dummy
var currentUser by mutableStateOf(
    User(
        name = "Dillon Bonay",
        userId = "123456789",
        email = "dilonbonay@gmail.com", // Nantinya bisa diisi dari data login
        phoneNumber = "081234567890",
        gender = "Male",
        dateOfBirth = "30 February 2000"
    )
)