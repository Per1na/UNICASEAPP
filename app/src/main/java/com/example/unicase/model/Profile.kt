package com.example.unicase.model

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Variabel global untuk menyimpan state foto profil pengguna.
 * State ini akan bertahan selama aplikasi berjalan, sehingga tidak hilang saat berpindah halaman.
 */
var userProfileImageUri by mutableStateOf<Uri?>(null)