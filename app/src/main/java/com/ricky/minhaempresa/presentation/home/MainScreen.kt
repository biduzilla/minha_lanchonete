package com.ricky.minhaempresa.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ricky.minhaempresa.navigation.NavigationBottom
import com.ricky.minhaempresa.presentation.home.components.BottomNavigation
import com.ricky.minhaempresa.presentation.home.components.DialogAddProduto
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Switch(modifier = Modifier.padding(24.dp), checked = state.value.darkMode,
                    thumbContent = {
                        if (state.value.darkMode) {
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
                        viewModel.onEvent(MainEvent.OnChangeTheme(isDark = it))
                    })
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController) {
                viewModel.onEvent(MainEvent.IsTelaProduto(it))
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MainEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {

        if (state.value.isDialogShow && state.value.isProdutos) {
            DialogAddProduto(
                state = state,
                onChangeNome = { viewModel.onEvent(MainEvent.OnChangeNome(it)) },
                onChangeTipo = { viewModel.onEvent(MainEvent.OnChangeTipo(it)) },
                onChangeMedida = { viewModel.onEvent(MainEvent.OnChangeMedida(it)) },
                onAddProduto = { viewModel.onEvent(MainEvent.AddProduto) },
                onDimiss = { viewModel.onEvent(MainEvent.ShowDialog) },
            )
        }
        if(state.value.isDialogShow && !state.value.isProdutos){
            Toast.makeText(context, "Faturamento", Toast.LENGTH_SHORT).show()
        }

        NavigationBottom(navController = navController)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MinhaEmpresaTheme {
        MainScreen()
    }
}