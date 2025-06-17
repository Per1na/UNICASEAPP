package com.example.unicase.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Definisikan skema warna untuk mode terang (Light Mode)
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlack,
    tertiary = TertiaryWhite,
    background = TertiaryWhite,
    surface = TertiaryWhite,
    onPrimary = TertiaryWhite,
    onSecondary = TertiaryWhite,
    onBackground = SecondaryBlack,
    onSurface = SecondaryBlack,
)

/*
 * Untuk saat ini, kita bisa samakan saja DarkColorScheme dengan LightColorScheme.
 * Nanti bisa disesuaikan jika ada desain khusus untuk dark mode.
*/
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlack,
    tertiary = TertiaryWhite,
    background = TertiaryWhite,
    surface = TertiaryWhite,
    onPrimary = TertiaryWhite,
    onSecondary = TertiaryWhite,
    onBackground = SecondaryBlack,
    onSurface = SecondaryBlack,
)


@Composable
fun UnicaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // Untuk aplikasi ini, kita non-aktifkan dark theme agar selalu menggunakan LightColorScheme
        // darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Typography dari file Type.kt
        content = content
    )
}