package com.ricky.minhaempresa.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Store
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    object Produto : BottomNavItem(
        title = "Estoque",
        selectedIcon = Icons.Filled.Store,
        unselectedIcon = Icons.Outlined.Store,
        route = "estoque"
    )

    object Balanco : BottomNavItem(
        title = "Faturamento",
        selectedIcon = Icons.Filled.Payments,
        unselectedIcon = Icons.Outlined.Payments,
        route = "faturamento"
    )
}