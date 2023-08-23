package com.ricky.minhaempresa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ricky.minhaempresa.presentation.balanco.BalancoScreen
import com.ricky.minhaempresa.presentation.balanco.FaturamentoViewModel
import com.ricky.minhaempresa.presentation.produtos.ProdutoScreen
import com.ricky.minhaempresa.presentation.produtos.ProdutoViewModel

@Composable
fun NavigationBottom(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Produto.route) {
        composable(BottomNavItem.Produto.route) {
            val viewModel = hiltViewModel<ProdutoViewModel>()
            val state by viewModel.state.collectAsState()
            ProdutoScreen(state = state, viewModel::onEvent)
        }
        composable(BottomNavItem.Balanco.route) {
            val viewModel = hiltViewModel<FaturamentoViewModel>()
            val state by viewModel.state.collectAsState()
            BalancoScreen(state = state)
        }
    }
}