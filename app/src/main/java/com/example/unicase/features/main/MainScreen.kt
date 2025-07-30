package com.example.unicase.features.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unicase.features.home.HomeScreen
import com.example.unicase.features.wishlist.WishlistScreen
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.TertiaryWhite
import com.example.unicase.ui.theme.UnicaseTheme

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

@Composable
fun MainScreen(mainNavController: NavHostController) {
    val bottomBarNavController = rememberNavController()

    val navigationItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("Chat", Icons.Default.Chat, "chat"),
        BottomNavItem("Wishlist", Icons.Default.Favorite, "wishlist"),
        BottomNavItem("History", Icons.Default.ReceiptLong, "history"),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AppNavHost(
            bottomBarNavController = bottomBarNavController,
            mainNavController = mainNavController,
            modifier = Modifier.fillMaxSize()
        )

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            NavigationBar(containerColor = TertiaryWhite) {
                val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                navigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            bottomBarNavController.navigate(item.route) {
                                popUpTo(bottomBarNavController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    bottomBarNavController: NavHostController,
    mainNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = bottomBarNavController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(navController = mainNavController) }
        composable("chat") { PlaceholderScreen("Chat Screen") }
        composable("wishlist") { WishlistScreen(navController = mainNavController) }
        composable("history") { PlaceholderScreen("History Screen") }
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UnicaseTheme {
        MainScreen(rememberNavController())
    }
}