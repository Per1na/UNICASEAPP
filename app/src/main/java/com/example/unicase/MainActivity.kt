package com.example.unicase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.unicase.navigation.AppNavigation
import com.example.unicase.ui.theme.UnicaseTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install splash screen dari API
        installSplashScreen()

        setContent {
            UnicaseTheme {
                AppNavigation() // Panggil kerangka navigasi utama kita
            }
        }
    }
}