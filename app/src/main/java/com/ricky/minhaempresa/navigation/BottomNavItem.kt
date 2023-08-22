package com.ricky.minhaempresa.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    object Produto : BottomNavItem(
        title = "Estoque",
        selectedIcon = Icons.Filled.Inventory2,
        unselectedIcon = Icons.Outlined.Inventory2,
        route = "estoque"
    )

    object Balanco : BottomNavItem(
        title = "Faturamento",
        selectedIcon = Icons.Filled.RequestQuote,
        unselectedIcon = Icons.Outlined.RequestQuote,
        route = "faturamento"
    )
}