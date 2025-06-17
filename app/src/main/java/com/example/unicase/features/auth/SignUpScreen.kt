package com.example.unicase.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
            onValueChange = { name = it },
            label = { Text("Name",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(14.dp),
            singleLine = true,
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
            onValueChange = { email = it },
            label = { Text("Email",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(14.dp),
            singleLine = true,
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
            onValueChange = { password = it },
            label = { Text("Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(14.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(14.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            onClick = { /* TODO: Logic untuk Sign Up */ },
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