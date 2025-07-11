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
fun SignUpScreen(navController: NavController) {
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

    // State untuk notifikasi slide-down
    var showSuccessNotification by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_unicase_logo2),
                    contentDescription = "Unicase Logo",
                    modifier = Modifier
                        .padding(bottom = 48.dp)
                        .scale(1.5f)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it; nameError = null },
                    label = { Text("Name", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    isError = nameError != null,
                    supportingText = { if (nameError != null) Text(nameError!!, color = MaterialTheme.colorScheme.error) },
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
                    value = email,
                    onValueChange = { email = it; emailError = null },
                    label = { Text("Email", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = emailError != null,
                    supportingText = { if (emailError != null) Text(emailError!!, color = MaterialTheme.colorScheme.error) },
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
                    onValueChange = { password = it; passwordError = null },
                    label = { Text("Password", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible}) {
                            Icon(imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, "Toggle password visibility", tint = PrimaryBlue)
                        }
                    },
                    isError = passwordError != null,
                    supportingText = { if (passwordError != null) Text(passwordError!!, color = MaterialTheme.colorScheme.error) },
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
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it; confirmPasswordError = null },
                    label = { Text("Confirm Password", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    visualTransformation = if(confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible}) {
                            Icon(imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, "Toggle password visibility", tint = PrimaryBlue)
                        }
                    },
                    isError = confirmPasswordError != null,
                    supportingText = { if (confirmPasswordError != null) Text(confirmPasswordError!!, color = MaterialTheme.colorScheme.error) },
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it },
                        colors = CheckboxDefaults.colors(checkedColor = PrimaryBlue)
                    )
                    TextButton(onClick = { navController.navigate("terms") }) {
                        Text(text = "I agree to the terms & conditions",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onBackground)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        nameError = null
                        emailError = null
                        passwordError = null
                        confirmPasswordError = null
                        var formIsValid = true

                        if (name.isBlank()) {
                            nameError = "Name cannot be empty"
                            formIsValid = false
                        }
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
                                passwordError = "Password must contain letter, number, and symbol"
                                formIsValid = false
                            }
                        }

                        if (confirmPassword != password) {
                            confirmPasswordError = "Passwords do not match"
                            formIsValid = false
                        }

                        if (formIsValid) {
                            showSuccessNotification = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    enabled = termsAccepted
                ) {
                    Text(text = "Sign Up",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White)
                }

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

        AnimatedVisibility(
            visible = showSuccessNotification,
            enter = slideInVertically(animationSpec = tween(500)) { fullHeight -> -fullHeight },
            exit = slideOutVertically(animationSpec = tween(500)) { fullHeight -> -fullHeight },
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            LaunchedEffect(Unit) {
                delay(2500L)
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            SuccessBanner()
        }
    }
}

@Composable
fun SuccessBanner() {
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
                "Registration Successful!",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun SignUpScreenPreview() {
    UnicaseTheme {
        SignUpScreen(rememberNavController())
    }
}