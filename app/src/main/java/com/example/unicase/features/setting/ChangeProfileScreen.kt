package com.example.unicase.features.setting

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.model.currentUser
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.UnicaseTheme

enum class InfoRowAction {
    NAVIGATE,
    COPY,
    NONE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfileScreen(navController: NavController) {
    val user = currentUser
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Change Profile", fontFamily = Poppins, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoRow(
                label = "Name",
                value = user.name,
                action = InfoRowAction.NAVIGATE,
                onClick = { navController.navigate("change_name") }
            )
            HorizontalDivider()
            ProfileInfoRow(
                label = "User ID",
                value = user.userId,
                action = InfoRowAction.COPY,
                onClick = {
                    // TODO: Logika untuk menyalin ke clipboard
                    Toast.makeText(context, "User ID Copied!", Toast.LENGTH_SHORT).show()
                }
            )
            HorizontalDivider()
            ProfileInfoRow(
                label = "E-mail",
                value = user.email,
                action = InfoRowAction.NAVIGATE,
                onClick = { /*TODO: Navigasi ke ganti email*/ }
            )
            HorizontalDivider()
            ProfileInfoRow(
                label = "Phone Number",
                value = user.phoneNumber,
                action = InfoRowAction.NAVIGATE,
                onClick = { /*TODO: Navigasi ke ganti no. telp*/ }
            )
            HorizontalDivider()
            ProfileInfoRow(
                label = "Gender",
                value = user.gender,
                action = InfoRowAction.NAVIGATE,
                onClick = { /*TODO: Navigasi ke ganti gender*/ }
            )
            HorizontalDivider()
            ProfileInfoRow(
                label = "Date of Birth",
                value = user.dateOfBirth,
                action = InfoRowAction.NAVIGATE,
                onClick = { /*TODO: Navigasi ke ganti tgl lahir*/ }
            )
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String, action: InfoRowAction, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = action != InfoRowAction.NONE, onClick = onClick)
            .padding(vertical = 20.dp), // Padding diperbesar agar lebih lega
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontFamily = Poppins,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(0.3f)
        )
        Row(
            modifier = Modifier.weight(0.65f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, fontFamily = Poppins, fontWeight = FontWeight.Normal, color = Color.Gray, fontSize = 14.sp)

            when (action) {
                InfoRowAction.NAVIGATE -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Edit $label",
                        tint = Color.Black
                    )
                }
                InfoRowAction.COPY -> {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy $label",
                        tint = Color.Black
                    )
                }
                InfoRowAction.NONE -> {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeProfileScreenPreview() {
    UnicaseTheme {
        ChangeProfileScreen(rememberNavController())
    }
}