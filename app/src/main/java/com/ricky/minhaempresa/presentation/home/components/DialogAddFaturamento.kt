package com.ricky.minhaempresa.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.domain.model.ProdutoTipo
import com.ricky.minhaempresa.presentation.home.MainState
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAddFaturamento(
    modifier: Modifier = Modifier,
    state: MainState,
    onChangeEntrada: (String) -> Unit,
    onChangeNome: (String) -> Unit,
    onChangeSaida: (String) -> Unit,
    onDimiss: () -> Unit,
    onAddFaturamento: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.dialog_add_faturamento),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        shape = RoundedCornerShape(20.dp),
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                if (state.onErrorNomeFaturamento) {
                    TextError()
                }
                CustomTextField(
                    value = state.nomeFaturamento,
                    onChange = { onChangeNome(it) },
                    label = R.string.nome,
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = state.entrada,
                    onChange = { onChangeEntrada(it) },
                    label = R.string.entrada,
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = state.saida,
                    onChange = { onChangeSaida(it) },
                    label = R.string.saida,
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        onDismissRequest = { onDimiss() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onAddFaturamento() }) {
                    Text(text = stringResource(id = R.string.add_produto))
                }
                Button(onClick = { onDimiss() }) {
                    Text(text = stringResource(id = R.string.cancelar))
                }
            }
        },
    )
}

@Preview
@Composable
private fun DialogFaturamentoProduto() {
    val state = remember { mutableStateOf(MainState()) }
    MinhaEmpresaTheme {
        DialogAddFaturamento(
            state = MainState(),
            onChangeEntrada = {},
            onChangeSaida = {},
            onDimiss = {},
            onAddFaturamento = {},
            onChangeNome = {}
        )
    }
}