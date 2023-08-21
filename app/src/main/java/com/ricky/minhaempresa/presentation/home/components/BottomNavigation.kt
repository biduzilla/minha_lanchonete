package com.ricky.minhaempresa.presentation.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ricky.minhaempresa.navigation.BottomNavItem
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@Composable
fun BottomNavigation(navController: NavController) {

    val items = listOf(
        BottomNavItem.Produto,
        BottomNavItem.Balanco
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false
            )
        }

    }
}

@Preview
@Composable
fun BottomNavigationPreviewLightMode() {
    MinhaEmpresaTheme(darkTheme = false) {
        val context = LocalContext.current
        BottomNavigation(navController = NavController(context))
    }
}

@Preview
@Composable
fun BottomNavigationPreviewDarkMode() {
    MinhaEmpresaTheme(darkTheme = false) {
        val context = LocalContext.current
        BottomNavigation(navController = NavController(context))
    }
}