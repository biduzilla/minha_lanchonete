package com.ricky.minhaempresa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ricky.minhaempresa.presentation.balanco.BalancoScreen
import com.ricky.minhaempresa.presentation.produtos.ProdutoScreen

@Composable
fun NavigationBottom(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Produto.route) {
        composable(BottomNavItem.Produto.route) {
            ProdutoScreen()
        }
        composable(BottomNavItem.Balanco.route) {
            BalancoScreen()
        }
    }
}