// Lokasi: app/src/main/java/com/example/unicase/navigation/AppNavigation.kt

package com.example.unicase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.unicase.features.auth.*
import com.example.unicase.features.cart.ShoppingCartScreen
import com.example.unicase.features.customization.CustomCaseScreen
import com.example.unicase.features.main.MainScreen
import com.example.unicase.features.main.PlaceholderScreen
import com.example.unicase.features.notification.NotificationScreen
import com.example.unicase.features.product.ProductDetailScreen
import com.example.unicase.features.profile.ProfileScreen
import com.example.unicase.features.search.SearchScreen
import com.example.unicase.features.setting.ChangeNameScreen
import com.example.unicase.features.setting.ChangeProfileScreen
import com.example.unicase.features.setting.SettingScreen
import com.example.unicase.features.splash.SplashScreen
import com.example.unicase.model.dummyProducts

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Ini "Manajer Utama"
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("signin") {
            SignInScreen(navController = navController)
        }
        composable("signup") {
            SignUpScreen(navController = navController)
        }
        composable("terms") {
            TermsScreen(navController = navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen(navController = navController)
        }
        composable("verify_code") {
            VerifyCodeScreen(navController = navController)
        }
        composable("new_password") {
            NewPasswordScreen(navController = navController)
        }
        composable("password_success") {
            PasswordSuccessScreen(navController = navController)
        }
        // --- PERUBAHAN DI SINI ---
        composable("main") {
            // Berikan NavController utama ke MainScreen
            MainScreen(mainNavController = navController)
        }
        // -------------------------
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            val product = dummyProducts.find { it.id == productId }
            if (product != null) {
                ProductDetailScreen(
                    navController = navController,
                    product = product
                )
            } else {
                PlaceholderScreen(text = "Product Not Found")
            }
        }
        composable("search") {
            SearchScreen(navController = navController)
        }
        composable("notification") {
            NotificationScreen(navController = navController)
        }
        composable("cart") {
            ShoppingCartScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("setting") {
            SettingScreen(navController = navController)
        }
        composable("change_profile") {
            ChangeProfileScreen(navController = navController)
        }
        composable("change_name") {
            ChangeNameScreen(navController = navController)
        }
        composable("custom_case") {
            CustomCaseScreen(navController = navController)
        }
    }
}

