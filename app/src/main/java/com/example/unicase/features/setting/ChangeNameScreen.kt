package com.example.unicase.features.setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.model.currentUser
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNameScreen(navController: NavController) {
    // State untuk menampung nama baru, diinisialisasi dengan nama saat ini
    var newName by remember { mutableStateOf(currentUser.name) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Change Name", fontFamily = Poppins, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Enter your full name to be displayed on your profile and orders.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("Name", fontFamily = Poppins, fontWeight = FontWeight.Normal) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
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
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    // Perbarui state global currentUser dengan nama yang baru
                    currentUser = currentUser.copy(name = newName)
                    // Kembali ke halaman sebelumnya
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeNameScreenPreview() {
    UnicaseTheme {
        ChangeNameScreen(rememberNavController())
    }
}