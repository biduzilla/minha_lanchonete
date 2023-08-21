package com.ricky.minhaempresa.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ricky.minhaempresa.navigation.NavigationBottom
import com.ricky.minhaempresa.presentation.home.components.BottomNavigation
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState()

    Scaffold(topBar = {
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
            BottomNavigation(navController = navController)
        }) {
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