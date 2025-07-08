package com.example.unicase.features.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Setting", fontWeight = FontWeight.Bold) },
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
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingMenuItem(
                icon = Icons.Default.Person,
                title = "Change Profile",
                subtitle = "Manage your identity and profile picture.",
                onClick = { navController.navigate("change_profile") }
            )
            HorizontalDivider()
            SettingMenuItem(
                icon = Icons.Default.LocationOn,
                title = "Address List",
                subtitle = "Manage your shopping delivery address.",
                onClick = { /* TODO: Navigasi ke Address List */ }
            )
            HorizontalDivider()
            SettingMenuItem(
                icon = Icons.Default.Security,
                title = "Account Security",
                subtitle = "Manage your Password.",
                onClick = { /* TODO: Navigasi ke Account Security */ }
            )
            // ------------------------------------------
        }
    }
}

@Composable
fun SettingMenuItem(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.padding(end = 16.dp)
                .size(36.dp),
            tint = Color.Black
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontFamily = Poppins, color = Color.Black, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Go to $title",
            tint = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    UnicaseTheme {
        SettingScreen(rememberNavController())
    }
}