package com.ricky.minhaempresa.presentation.produtos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.domain.model.ProdutoTipo
import com.ricky.minhaempresa.presentation.produtos.components.CardProduto
import com.ricky.minhaempresa.presentation.produtos.components.DialogRemoverProduto
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@Composable
fun ProdutoScreen(viewModel: ProdutoViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Box {

        if (state.value.isShowDialog) {
            DialogRemoverProduto(onDimiss = {
                viewModel.onEvent(ProdutoEvent.HideDialog)
            }, onRemoverProduto = {
                viewModel.onEvent(ProdutoEvent.OnRemoveProduto)
            })
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = stringResource(id = R.string.bebidas),
                    style = MaterialTheme.typography.headlineLarge
                )
                Divider()
            }
            items(state.value.produtos.filter { it.tipo == ProdutoTipo.BEBIDA }) { item ->
                CardProduto(
                    modifier = Modifier.padding(10.dp),
                    produto = item,
                    onAddQtd = { viewModel.onEvent(ProdutoEvent.OnAddQtd(id = item.id)) },
                    onRemoverQtd = { viewModel.onEvent(ProdutoEvent.OnRemoveQtd(id = item.id)) },
                    onExcluirProduto = { viewModel.onEvent(ProdutoEvent.ShowDialog(id = item.id)) })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(id = R.string.insumos),
                    style = MaterialTheme.typography.headlineLarge
                )
                Divider()
            }
            items(state.value.produtos.filter { it.tipo == ProdutoTipo.INSUMO }) { item ->
                CardProduto(
                    modifier = Modifier.padding(10.dp),
                    produto = item,
                    onAddQtd = { viewModel.onEvent(ProdutoEvent.OnAddQtd(id = item.id)) },
                    onRemoverQtd = { viewModel.onEvent(ProdutoEvent.OnRemoveQtd(id = item.id)) },
                    onExcluirProduto = { viewModel.onEvent(ProdutoEvent.ShowDialog(id = item.id)) })
            }
        }
    }
}

@Preview
@Composable
private fun ProdutoScreenPreview() {
    MinhaEmpresaTheme {
        ProdutoScreen()
    }
}