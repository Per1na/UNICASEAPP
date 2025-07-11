package com.example.unicase.features.profile

import android.content.Context
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.R
import com.example.unicase.datastore.UserPreferences
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


data class ProfileMenuItemData(
    @DrawableRes val iconRes: Int,
    val text: String,
    val isLogout: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val name by UserPreferences(context).getName().collectAsState(initial = "")
    Log.d("PROFILE", "Nama yang dibaca: $name")







    // Siapkan daftar menu dalam sebuah list
    val menuItems = listOf(
        ProfileMenuItemData(R.drawable.ic_wallet, "Pay"),
        ProfileMenuItemData(R.drawable.ic_truck, "Shipped"),
        ProfileMenuItemData(R.drawable.ic_delivered, "Delivered"),
        ProfileMenuItemData(R.drawable.ic_settings, "Setting"),
        ProfileMenuItemData(R.drawable.ic_costumer_service, "Chat Admin"),
        ProfileMenuItemData(R.drawable.ic_log_out, "Log Out", isLogout = true)
    )


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header sebagai item pertama
            item {
                Spacer(modifier = Modifier.height(24.dp))
                ProfileHeader(name = name ?: "")
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Garis pemisah sebagai item kedua
            item {
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }


            items(menuItems) { item ->
                // Panggil ProfileMenuItem di dalam padding
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ProfileMenuItem(
                        iconRes = item.iconRes,
                        text = item.text,
                        isLogout = item.isLogout,
                        navController = navController,
                        context = context
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box {
            Icon(
                imageVector = Icons.Default.AccountCircle, // Placeholder Ikon
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue),
                tint = Color.White
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue)
                    .border(BorderStroke(2.dp, Color.White), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
        }
        Text(text = name,
            fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
    }
}

// --- FUNGSI YANG DIPERBAIKI ---
@Composable
fun ProfileMenuItem(@DrawableRes
                    iconRes: Int,
                    text: String,
                    isLogout: Boolean = false,
                    navController: NavController,
                    context: android.content.Context) {

    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            if (isLogout) {
                coroutineScope.launch {
                    UserPreferences(context).clearToken()
                    navController.navigate("signin") {
                        popUpTo("main") { inclusive = true } // agar tidak bisa kembali pakai back
                    }
                }
            } else {
                // TODO: Aksi lainnya (misalnya navigasi ke halaman setting, shipped, dll)
            }
        }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            tint = if (isLogout) Color.Red else Color.Black,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            color = if (isLogout) Color.Red else Color.Black
        )

    }
}

// ------------------------------


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    UnicaseTheme {
        ProfileScreen(rememberNavController())
    }
}