package com.example.unicase.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.unicase.R
import com.example.unicase.ui.theme.BrandBlue
import com.example.unicase.ui.theme.UnicaseTheme
import com.example.unicase.ui.theme.TertiaryWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) { // NavController untuk pindah halaman
    // LaunchedEffect akan menjalankan blok kode coroutine saat composable pertama kali muncul
    LaunchedEffect(key1 = true) {
        delay(3000L) // Tunda selama 3 detik
        // Setelah 3 detik, hapus splash dari backstack dan pindah ke halaman welcome
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // Box adalah container seperti FrameLayout, bagus untuk menumpuk elemen
    Box(
        modifier = Modifier
            .fillMaxSize() // Memenuhi seluruh layar
            .background(BrandBlue),
        contentAlignment = Alignment.Center // Menempatkan konten di tengah
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_unicase_logo),
            contentDescription = "Unicase Logo",
            modifier = Modifier.fillMaxSize(0.80f) // Mengurangi ukuran logo

        )
    }
}

// @Preview digunakan untuk melihat tampilan UI di Android Studio tanpa menjalankan aplikasi
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    UnicaseTheme {
        // Karena preview tidak punya NavController sungguhan, kita bisa membuat mock-nya
        // atau cukup panggil UI-nya saja jika tidak ada logic navigasi yang kompleks.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BrandBlue),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_unicase_logo),
                contentDescription = "Unicase Logo",
                modifier = Modifier.fillMaxSize(0.80f) // Mengurangi ukuran logo
            )
        }
    }
}