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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.domain.model.ProdutoTipo
import com.ricky.minhaempresa.presentation.home.MainState
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAddProduto(
    modifier: Modifier = Modifier,
    state: State<MainState>,
    onChangeNome: (String) -> Unit,
    onChangeTipo: (ProdutoTipo) -> Unit,
    onChangeMedida: (String) -> Unit,
    onDimiss: () -> Unit,
    onAddProduto: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.dialog_add_produto),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        shape = RoundedCornerShape(20.dp),
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                if (state.value.onErrorNome) {
                    TextError()
                }

                CustomTextField(
                    value = state.value.nome,
                    onChange = { onChangeNome(it) },
                    label = R.string.nome_produto
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (state.value.onErrorMedida) {
                    TextError()
                }

                CustomTextField(
                    value = state.value.medida,
                    onChange = { onChangeMedida(it) },
                    label = R.string.medida
                )

                Spacer(modifier = Modifier.height(16.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = state.value.tipo.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = ProdutoTipo.BEBIDA.name) },
                            onClick = {
                                onChangeTipo(ProdutoTipo.BEBIDA)
                                expanded = false
                            }

                        )
                        DropdownMenuItem(
                            text = { Text(text = ProdutoTipo.INSUMO.name) },
                            onClick = {
                                onChangeTipo(ProdutoTipo.INSUMO)
                                expanded = false
                            }
                        )

                    }
                }
            }
        },
        onDismissRequest = { onDimiss() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onAddProduto() }) {
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
private fun DialogAddProdutoPreview() {
    val state = remember { mutableStateOf(MainState()) }
    MinhaEmpresaTheme {
        val context = LocalContext.current
        DialogAddProduto(
            onChangeMedida = {},
            onChangeNome = {},
            onChangeTipo = {},
            state = state,
            onDimiss = {}
        ) {}
    }
}