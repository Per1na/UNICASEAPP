package com.example.unicase.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.unicase.features.addaddress.AddAddressScreen
import com.example.unicase.features.addresslist.AddressListScreen
import com.example.unicase.features.auth.*
import com.example.unicase.features.cart.ShoppingCartScreen
import com.example.unicase.features.customization.CheckoutScreen
import com.example.unicase.features.customization.CustomCaseScreen
import com.example.unicase.features.customization.CustomizationViewModel
import com.example.unicase.features.main.MainScreen
import com.example.unicase.features.main.PlaceholderScreen
import com.example.unicase.features.notification.NotificationScreen
import com.example.unicase.features.payment.PaymentScreen
import com.example.unicase.features.product.ProductDetailScreen
import com.example.unicase.features.profile.ProfileScreen
import com.example.unicase.features.search.SearchScreen
import com.example.unicase.features.setting.ChangeNameScreen
import com.example.unicase.features.setting.ChangeProfileScreen
import com.example.unicase.features.setting.SettingScreen
import com.example.unicase.features.splash.SplashScreen
import com.example.unicase.model.AddressListViewModel
import com.example.unicase.model.TransactionHistoryScreen
import com.example.unicase.model.dummyProducts

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Ini "Manajer Utama"

    // --- PERUBAHAN PENTING 1 ---
    // Buat ViewModel di sini agar bisa dibagikan ke semua layar di bawahnya.
    val customizationViewModel: CustomizationViewModel = viewModel()
    val addressListViewModel: AddressListViewModel = viewModel()


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
        composable("main") {
            MainScreen(mainNavController = navController)
        }
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

        // --- PERUBAHAN PENTING 2 ---
        // Sekarang teruskan ViewModel yang sama ke kedua layar.
        composable("custom_case") {
            CustomCaseScreen(
                navController = navController,
                customizationViewModel = customizationViewModel
            )
        }
        composable("checkout") {
            CheckoutScreen(
                navController = navController,
                customizationViewModel = customizationViewModel
            )
        }
        composable("address_list") {
            AddressListScreen(
                navController = navController,
                customizationViewModel = customizationViewModel
            )
        }
        composable("add_address") {
            AddAddressScreen(
                navController = navController,
                customizationViewModel = customizationViewModel
            )
        }
        composable(
            route = "payment/{totalAmount}",
            arguments = listOf(navArgument("totalAmount") { type = NavType.IntType })
        ) { backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getInt("totalAmount") ?: 0
            PaymentScreen(
                navController = navController,
                totalAmount = totalAmount
            )
        }
        composable("transactionHistory") {
            TransactionHistoryScreen(navController)
        }
    }
}