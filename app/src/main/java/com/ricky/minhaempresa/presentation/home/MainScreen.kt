package com.ricky.minhaempresa.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ricky.minhaempresa.navigation.NavigationBottom
import com.ricky.minhaempresa.presentation.home.components.BottomNavigation
import com.ricky.minhaempresa.presentation.home.components.DialogAddFaturamento
import com.ricky.minhaempresa.presentation.home.components.DialogAddProduto
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Switch(modifier = Modifier.padding(24.dp), checked = state.darkMode,
                    thumbContent = {
                        if (state.darkMode) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.LightMode,
                                contentDescription = null,
                            )
                        }
                    },
                    onCheckedChange = {
                        onEvent(MainEvent.OnChangeTheme(isDark = it))
                    })
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController) {
               onEvent(MainEvent.IsTelaProduto(it))
            }
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(65.dp), onClick = {
                onEvent(MainEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = if (state.isProdutos) Icons.Default.AddShoppingCart else Icons.Default.PostAdd,
                    contentDescription = null
                )
            }
        }
    ) {

        if (state.isDialogShow && state.isProdutos) {
            DialogAddProduto(
                state = state,
                onChangeNome = { onEvent(MainEvent.OnChangeNome(it)) },
                onChangeTipo = { onEvent(MainEvent.OnChangeTipo(it)) },
                onChangeMedida = { onEvent(MainEvent.OnChangeMedida(it)) },
                onAddProduto = { onEvent(MainEvent.AddProduto) },
                onDimiss = { onEvent(MainEvent.ShowDialog) },
            )
        }
        if (state.isDialogShow && !state.isProdutos) {
            DialogAddFaturamento(
                state = state,
                onChangeEntrada = { onEvent(MainEvent.OnChangeEntrada(it)) },
                onChangeSaida = { onEvent(MainEvent.OnChangeSaida(it)) },
                onDimiss = { onEvent(MainEvent.ShowDialog) },
                onAddFaturamento = { onEvent(MainEvent.AddFaturamento) },
                onChangeNome = { onEvent(MainEvent.OnChangeNomeFaturamento(it)) })
        }

        NavigationBottom(navController = navController)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MinhaEmpresaTheme {
        MainScreen(state =MainState()) {}
    }
}