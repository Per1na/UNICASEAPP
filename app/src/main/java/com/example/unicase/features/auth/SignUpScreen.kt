package com.example.unicase.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.R
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@Composable
fun SignUpScreen(navController: NavController) {
    // State untuk semua input field
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var termsAccepted by rememberSaveable { mutableStateOf(false) }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo UNICASE
        Image(
            painter = painterResource(id = R.drawable.ic_unicase_logo2),
            contentDescription = "Unicase Logo",
            modifier = Modifier.padding(bottom = 48.dp)
                .scale(1.5f)
        )

        // INPUT FIELDS

        //Input Field Untuk Nama
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it; nameError = null },
            label = { Text("Name",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal)
                    },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            isError = nameError != null,
            supportingText = {
                if (nameError != null) Text(nameError!!,
                    color = MaterialTheme.colorScheme.error)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = Color.Gray,
            cursorColor = PrimaryBlue,
            focusedLabelColor = PrimaryBlue,
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Input Field Untuk Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it; emailError = null},
            label = { Text("Email",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            isError = emailError != null,
            supportingText = {
                if (emailError != null) Text(emailError!!,
                    color = MaterialTheme.colorScheme.error)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = PrimaryBlue,
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        //Input Field Untuk Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it; passwordError = null },
            label = { Text("Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible}) {
                    Icon(imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        "Toggle password visibility",
                        tint = PrimaryBlue)
                }
            },
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) Text(passwordError!!, color = MaterialTheme.colorScheme.error)
            },

            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = PrimaryBlue,
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        //Input Field Untuk Konfirmasi Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it },
            label = { Text("Confirm Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            visualTransformation = if(confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible}) {
                    Icon(imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        "Toggle password visibility",
                        tint = PrimaryBlue)
                }
            },
            isError = confirmPasswordError != null,
            supportingText = {
                if (confirmPasswordError != null) Text(confirmPasswordError!!, color = MaterialTheme.colorScheme.error)
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = PrimaryBlue,
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )


        Spacer(modifier = Modifier.height(16.dp))


        // Checkbox Syarat dan Ketentuan
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = { termsAccepted = it },
                colors = CheckboxDefaults.colors(checkedColor = PrimaryBlue)
            )
            // Teks ini nantinya bisa diklik untuk membuka halaman Terms & Conditions
            TextButton(onClick = {
                navController.navigate("terms") // <-- UBAH INI
            }) {
                Text(text = "I agree to the terms & conditions",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Sign Up
        Button(
            onClick = {
                nameError = null
                emailError = null
                passwordError = null
                confirmPasswordError = null
                var formIsValid = true

                // Validasi Nama
                if (name.isBlank()) {
                    nameError = "Name cannot be empty"
                    formIsValid = false
                }

                // Validasi Email
                if (email.isBlank()) {
                    emailError = "E-mail cannot be empty"
                    formIsValid = false
                } else if (!email.endsWith("@gmail.com", ignoreCase = true)) {
                    emailError = "Invalid E-mail format"
                    formIsValid = false
                }

                // Validasi Password
                if (password.isBlank()) {
                    passwordError = "Password cannot be empty"
                    formIsValid = false
                } else if (password.length < 8) {
                    passwordError = "Passwordm= must be at least 8 characters"
                    formIsValid = false
                } else {
                    val containLetter = password.any { it.isLetter() }
                    val containNumber = password.any { it.isDigit() }
                    val containSymbol = password.any { !it.isLetterOrDigit() }

                    if (!containLetter || !containNumber || !containSymbol) {
                        passwordError = "Password must contain at least letter, number, and symbol"
                        formIsValid = false
                    }
                }

                // Validasi Confirm Password
                if (confirmPassword.isBlank()) {
                    confirmPasswordError = "Confirm Password cannot be empty"
                    formIsValid = false
                } else if (confirmPassword != password) {
                    confirmPasswordError = "Confirm Password does not match"
                }

                // Jika sudah semua
                if (formIsValid) {
                    // TODO: Logic Sign Up yang sebenarnya (misal: panggil API)
                    // Untuk sekarang, kita navigasi ke main screen
                    navController.navigate("main") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            enabled = termsAccepted // Tombol hanya aktif jika checkbox dicentang
        ) {
            Text(text = "Sign Up",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
        }

        // Tautan ke halaman Sign In
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already account?",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                color = Color.Gray)
            TextButton(onClick = { navController.navigate("signin") }) {
                Text(text = "Sign In",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = PrimaryBlue)
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun SignUpScreenPreview() {
    UnicaseTheme {
        SignUpScreen(navController = rememberNavController())
    }
}