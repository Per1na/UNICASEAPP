package com.example.unicase.features.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
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
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // State untuk notifikasi slide-down
    var showSuccessNotification by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_unicase_logo2),
                contentDescription = "Unicase Logo",
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .scale(1.5f)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                label = { Text("Email", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) {
                        Text(emailError!!, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = null
                },
                label = { Text("Password", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible}) {
                        Icon(imageVector = image, description, tint = PrimaryBlue)
                    }
                },
                isError = passwordError != null,
                supportingText = {
                    if (passwordError != null) {
                        Text(passwordError!!, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            TextButton(
                onClick = {
                    navController.navigate("forgot_password")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Forgot Password?", fontFamily = Poppins, fontWeight = FontWeight.SemiBold, color = PrimaryBlue)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    emailError = null
                    passwordError = null
                    var formIsValid = true

                    if (email.isBlank()) {
                        emailError = "Email cannot be empty"
                        formIsValid = false
                    } else if (!email.endsWith("@gmail.com", ignoreCase = true)) {
                        emailError = "Invalid email format"
                        formIsValid = false
                    }

                    if (password.isBlank()) {
                        passwordError = "Password cannot be empty"
                        formIsValid = false
                    } else if (password.length < 8) {
                        passwordError = "Password must be at least 8 characters"
                        formIsValid = false
                    } else {
                        val containsLetter = password.any { it.isLetter() }
                        val containsDigit = password.any { it.isDigit() }
                        val containsSymbol = password.any { !it.isLetterOrDigit() }

                        if (!containsLetter || !containsDigit || !containsSymbol) {
                            passwordError = "Password must contain at least letter, number, and symbol"
                            formIsValid = false
                        }
                    }
                    if (formIsValid) {
                        // Tampilkan notifikasi, jangan langsung navigasi
                        showSuccessNotification = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(text = "Sign In", fontFamily = Poppins, fontWeight = FontWeight.SemiBold, color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Don't have an account?", fontFamily = Poppins, fontWeight = FontWeight.Normal, color = Color.Gray)
                TextButton(onClick = { navController.navigate("signup") }) {
                    Text(text = "Sign Up", fontFamily = Poppins, fontWeight = FontWeight.SemiBold, color = PrimaryBlue, fontSize = 16.sp)
                }
            }
        }

        // AnimatedVisibility untuk notifikasi
        AnimatedVisibility(
            visible = showSuccessNotification,
            enter = slideInVertically(animationSpec = tween(500)) { fullHeight -> -fullHeight },
            exit = slideOutVertically(animationSpec = tween(500)) { fullHeight -> -fullHeight },
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            // Timer dan navigasi
            LaunchedEffect(Unit) {
                delay(2500L) // Tampil selama 2.5 detik
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            // Tampilan banner notifikasi
            LoginSuccessBanner()
        }
    }
}

@Composable
fun LoginSuccessBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = Color(0xFF4CAF50)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "Login Successful!",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun SignInScreenPreview() {
    UnicaseTheme {
        SignInScreen(rememberNavController())
    }
}