package com.example.diagnosaallapps.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.diagnosaallapps.ui.navigation.Screen

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
