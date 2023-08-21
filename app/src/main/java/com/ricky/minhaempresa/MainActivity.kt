package com.ricky.minhaempresa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.ricky.minhaempresa.common.DataStoreUtil
import com.ricky.minhaempresa.presentation.home.MainScreen
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStoreUtil = DataStoreUtil(applicationContext)
        var darkMode by mutableStateOf(false)


        lifecycleScope.launch {
            dataStoreUtil.getTheme().collect {
                darkMode = it
            }
        }

        setContent {
            MinhaEmpresaTheme(darkTheme = darkMode) {
                MainScreen()
            }
        }
    }
}
