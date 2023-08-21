package com.ricky.minhaempresa.presentation.produtos.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.presentation.home.MainState
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogRemoverProduto(
    modifier: Modifier = Modifier,
    onDimiss: () -> Unit,
    onRemoverProduto: () -> Unit
) {

    AlertDialog(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        text = {
            Text(
                text = stringResource(id = R.string.deseja_apagar_produto),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        onDismissRequest = { onDimiss() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onRemoverProduto() }) {
                    Text(text = stringResource(id = R.string.apagar_produto))
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
private fun DialogAddProdutoPreview() {
    MinhaEmpresaTheme {
        DialogRemoverProduto(
            onRemoverProduto = {},
            onDimiss = {}
        )
    }
}