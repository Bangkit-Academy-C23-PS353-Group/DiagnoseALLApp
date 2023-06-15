package com.example.diagnosaallapps.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("Home")
    object History: Screen("History")
    object Upload: Screen("Upload")
    object Profile: Screen("Profile")
}
