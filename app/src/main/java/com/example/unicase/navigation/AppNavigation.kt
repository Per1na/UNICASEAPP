// Lokasi: app/src/main/java/com/example/unicase/navigation/AppNavigation.kt

package com.example.unicase.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.unicase.features.Preview.HomeScreenPreview
import com.example.unicase.features.Preview.ProductDetailScreenPreview
import com.example.unicase.features.Preview.ProfilePreviewScreen
import com.example.unicase.features.Preview.SearchPreview
import com.example.unicase.features.addaddress.AddAddressScreen
import com.example.unicase.features.addresslist.AddressListScreen
import com.example.unicase.features.auth.*
import com.example.unicase.features.cart.ShoppingCartScreen
import com.example.unicase.features.checkout.CheckoutScreen
import com.example.unicase.features.customization.CustomCaseScreen
import com.example.unicase.features.customization.CustomizationViewModel
import com.example.unicase.features.home.HomeScreen
import com.example.unicase.features.main.MainScreen
import com.example.unicase.features.main.PlaceholderScreen
import com.example.unicase.features.notification.NotificationScreen
import com.example.unicase.features.payment.PaymentScreen
import com.example.unicase.features.product.ProductDetailScreen
import com.example.unicase.features.profile.ProfileScreen
import com.example.unicase.features.profile.ProfileScreenPreview
import com.example.unicase.features.search.SearchScreen
import com.example.unicase.features.search.SearchScreenPreview
import com.example.unicase.features.setting.SettingScreen
import com.example.unicase.features.splash.SplashScreen
import com.example.unicase.features.transaction.TransactionHistoryScreen
import com.example.unicase.model.AddressListViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Ini "Manajer Utama"
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
        // --- PERUBAHAN DI SINI ---
        composable("main") {
            // Berikan NavController utama ke MainScreen
            MainScreen(mainNavController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController)
        }
        // -------------------------

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

        composable("custom_case") {
            val customizationViewModel: CustomizationViewModel = viewModel()
            CustomCaseScreen(
                navController = navController,
                customizationViewModel = customizationViewModel
            )
        }

        composable("checkout") {
            CheckoutScreen(navController = navController,
                addressListViewModel = addressListViewModel)
        }
        composable("Buy") {
            CheckoutScreen(navController = navController)
        }


        composable("Let Get Started") {
            HomeScreenPreview(navController = navController)
        }

        composable("ProfilePreview") {
            ProfilePreviewScreen(navController = navController)
        }

        composable("PreviewSearch") {
            SearchPreview(navController = navController)
        }

        composable("ProductPreview") {
            ProductDetailScreenPreview(productId = 1, navController = navController)
        }

        composable(
            route = "product_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("id") ?: return@composable
            ProductDetailScreen(productId = productId, navController = navController)
        }
        composable("transactionHistory") {
            TransactionHistoryScreen(navController)
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
    }
}

