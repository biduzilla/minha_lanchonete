package com.ricky.minhaempresa.presentation.balanco

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.presentation.balanco.components.ListViewEntradas

@Composable
fun BalancoScreen(
    state: FaturamentoState,
) {
    LazyColumn(Modifier.padding(20.dp)) {
        item {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.faturamentos),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        items(state.faturamentos) { faturamento ->
            ListViewEntradas(
                faturamentos = faturamento,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
    }
}