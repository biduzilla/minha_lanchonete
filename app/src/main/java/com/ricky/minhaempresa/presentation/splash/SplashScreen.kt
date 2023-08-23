package com.ricky.minhaempresa.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.navigation.Screens
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@Composable
fun SplashScreen(state: SplashState, navController: NavController) {
//    val state = viewModel.state.collectAsState()

    if (state.loading) {
        navController.navigate(Screens.HomeScreen.route)
    }

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                modifier = Modifier.size(250.dp),
                imageVector = Icons.Default.Store,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MinhaEmpresaTheme {
        val state: State<SplashState> = remember { mutableStateOf(SplashState()) }
        val context = LocalContext.current
        SplashScreen(
            navController = NavController(context),
            state = SplashState()
        )
    }
}