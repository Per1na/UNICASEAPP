package com.example.unicase.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
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
fun SignInScreen(navController: NavController) {
    // State untuk menampung input dari pengguna
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Pusatkan konten secara vertikal
    ) {
        // Logo UNICASE
        Image(
            painter = painterResource(id = R.drawable.ic_unicase_logo2),
            contentDescription = "Unicase Logo",
            modifier = Modifier.padding(bottom = 64.dp)
                .scale(1.5f)
        )

        // Input field untuk Email
        OutlinedTextField(
    value = email,
    onValueChange = { email = it },
    label = { Text("Email",
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal) },
    modifier = Modifier.
    fillMaxWidth(), shape = RoundedCornerShape(14.dp),
    singleLine = true,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

        // Input field untuk Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

        // Tombol Lupa Password
        TextButton(
            onClick = {
                navController.navigate("forgot_password") // <-- UBAH INI
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Forgot Password?", fontFamily = Poppins, fontWeight = FontWeight.SemiBold, color = PrimaryBlue)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Sign In
        Button(
            onClick = {
                navController.navigate("main") {
                    popUpTo("signin") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
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