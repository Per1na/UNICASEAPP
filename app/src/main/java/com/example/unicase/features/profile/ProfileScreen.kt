package com.example.unicase.features.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.model.userProfileImageUri
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme
import java.io.File

data class ProfileMenuItemData(
    @DrawableRes val iconRes: Int,
    val text: String,
    val isLogout: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val menuItems = listOf(
        ProfileMenuItemData(R.drawable.ic_wallet, "Pay"),
        ProfileMenuItemData(R.drawable.ic_truck, "Shipped"),
        ProfileMenuItemData(R.drawable.ic_delivered, "Delivered"),
        ProfileMenuItemData(R.drawable.ic_settings, "Setting"),
        ProfileMenuItemData(R.drawable.ic_costumer_service, "Chat Admin"),
        ProfileMenuItemData(R.drawable.ic_log_out, "Log Out", isLogout = true)
    )

    var showOptionsDialog by remember { mutableStateOf(false) }
    var showFullScreenImage by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) } // State untuk dialog logout

    val context = LocalContext.current
    val isNotInPreview = !LocalInspectionMode.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> userProfileImageUri = uri }
    )

    val tempImageUri = if (isNotInPreview) {
        remember {
            val file = File(context.cacheDir, "temp_image.jpg")
            FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        }
    } else { null }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) { userProfileImageUri = tempImageUri }
        }
    )

    if (showOptionsDialog) {
        ImagePickerOptionsDialog(
            onDismissRequest = { showOptionsDialog = false },
            onGalleryClick = {
                showOptionsDialog = false
                galleryLauncher.launch("image/*")
            },
            onCameraClick = {
                showOptionsDialog = false
                tempImageUri?.let { uri -> cameraLauncher.launch(uri) }
            }
        )
    }

    if (showFullScreenImage && userProfileImageUri != null) {
        Dialog(onDismissRequest = { showFullScreenImage = false }) {
            AsyncImage(
                model = userProfileImageUri,
                contentDescription = "Full Screen Profile Picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Log Out") },
            text = { Text("Are you sure want to Log Out?") },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        // TODO: Logika hapus sesi/data pengguna
                        // Navigasi ke halaman login dan bersihkan semua halaman sebelumnya
                        navController.navigate("signin") {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Warna Hijau
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showLogoutDialog = false }, // Hanya tutup dialog
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red) // Warna Merah
                ) {
                    Text("No")
                }
            }
        )
    }

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
            item {
                Spacer(modifier = Modifier.height(24.dp))
                ProfileHeader(
                    imageUri = userProfileImageUri,
                    onEditClick = { showOptionsDialog = true },
                    onProfileImageClick = {
                        if (userProfileImageUri != null) {
                            showFullScreenImage = true
                        }
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }

            items(menuItems) { item ->
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ProfileMenuItem(
                        iconRes = item.iconRes,
                        text = item.text,
                        isLogout = item.isLogout,
                        onClick = {
                            when (item.text) {
                                "Setting" -> navController.navigate("setting")
                                "Log Out" -> showLogoutDialog = true
                                else -> {
                                    // TODO: Navigasi untuk item lain
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePickerOptionsDialog(
    onDismissRequest: () -> Unit,
    onGalleryClick: () -> Unit,
    onCameraClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Ganti Foto Profil") },
        text = { Text("Pilih sumber gambar:") },
        confirmButton = {
            TextButton(onClick = onCameraClick) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Camera")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Kamera")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onGalleryClick) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PhotoLibrary, contentDescription = "Gallery")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galeri")
                }
            }
        }
    )
}

@Composable
fun ProfileHeader(imageUri: Uri?, onEditClick: () -> Unit, onProfileImageClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .clickable(onClick = onProfileImageClick),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(PrimaryBlue)
                        .clickable(onClick = onProfileImageClick),
                    tint = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue)
                    .border(BorderStroke(2.dp, Color.White), CircleShape)
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile",
                        modifier = Modifier.size(18.dp),
                        tint = Color.White
                    )
                }
            }
        }
        Text(text = "Dillon Bonay", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
    }
}

@Composable
fun ProfileMenuItem(
    @DrawableRes iconRes: Int,
    text: String,
    isLogout: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            tint = if (isLogout) Color.Red else Color.DarkGray,
            modifier = Modifier.size(46.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = if (isLogout) Color.Red else Color.DarkGray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    UnicaseTheme {
        ProfileScreen(rememberNavController())
    }
}