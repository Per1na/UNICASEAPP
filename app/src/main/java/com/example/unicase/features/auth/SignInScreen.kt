package com.example.unicase.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.R
import com.example.unicase.datastore.UserPreferences
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme
import com.example.unicase.viewmodel.AuthViewModel

@Composable
fun SignInScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    // State untuk menampung input dari pengguna
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val loginState = viewModel.loginState
    var loginErrorMessage by remember { mutableStateOf<String?>(null) }
    var loginAttempted by remember { mutableStateOf(false) }



    LaunchedEffect(loginState) {
        if (loginState != null && loginState.isSuccess) {
            val response = loginState.getOrNull()
            val token = response?.token
            val name = response?.user?.name

            token?.let {
                val prefs = UserPreferences(context)
                prefs.saveToken(it)
                prefs.saveName(name ?: "") // Simpan nama user yang login sekarang
                navController.navigate("main") {
                    popUpTo("signin") { inclusive = true }
                }
            }
        } else if (loginState != null && loginState.isFailure) {
            loginErrorMessage = "Email or password is incorrect"
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    )

    {
        Image(
            painter = painterResource(id = R.drawable.ic_unicase_logo2),
            contentDescription = "Unicase Logo",
            modifier = Modifier
                .padding(bottom = 64.dp)
                .scale(1.5f)
        )



        // Input field untuk Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
                loginErrorMessage = null


            },
            label = { Text("Email",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal)
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError != null,
            supportingText = {
                if ( emailError != null) {
                    Text(emailError!!, color = MaterialTheme.colorScheme.error)
                }
            },
            colors = OutlinedTextFieldDefaults.colors
                (focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = PrimaryBlue,
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input field untuk Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },
            label = { Text("Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal)
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            visualTransformation =
                if(passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible}) {
                    Icon(imageVector = image, description, tint = PrimaryBlue)
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
            ),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) {
                    Text(passwordError!!, color = MaterialTheme.colorScheme.error)
                }
            }
        )
        if (loginErrorMessage != null) {
            Text(
                text = loginErrorMessage!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )
        }



        // Tombol Lupa Password
        TextButton(
            onClick = {
                navController.navigate("forgot_password")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Forgot Password?", fontFamily = Poppins, fontWeight = FontWeight.SemiBold, color = PrimaryBlue)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Sign In
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
                }

                else if (password.length < 8) {
                    passwordError = "Password must be at least 8 characters"
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

                if (formIsValid) {
                    loginAttempted = true
                    loginErrorMessage = null
                    viewModel.login(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        )

        {
            Text(text = "Sign In",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
        }

        // Tautan ke halaman Sign Up
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                color = Color.Gray)
            TextButton(onClick = { navController.navigate("signup") }) {
                Text(text = "Sign Up",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryBlue,
                    fontSize = 16.sp)
            }

        }
    }
}



@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun SignInScreenPreview() {
    UnicaseTheme {
        SignInScreen(navController = rememberNavController())
    }
}