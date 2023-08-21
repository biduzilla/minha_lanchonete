package com.ricky.minhaempresa.presentation.produtos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.presentation.produtos.components.CardProduto
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@Composable
fun ProdutoScreen(viewModel: ProdutoViewModel = hiltViewModel()) {

    val state = viewModel.state

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.bebidas),
                    style = MaterialTheme.typography.headlineLarge
                )
                Divider()
            }
            itemsIndexed(state.value.bebidas) { index, item ->
                CardProduto(
                    modifier = Modifier.padding(10.dp),
                    produto = item,
                    onAddQtd = { viewModel.onEvent(ProdutoEvent.OnAddQtd(index = index)) },
                    onRemoverQtd = { viewModel.onEvent(ProdutoEvent.OnRemoveQtd(index = index)) },
                    onExcluirProduto = { viewModel.onEvent(ProdutoEvent.OnRemoveProduto(index = index)) })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(id = R.string.insumos),
                    style = MaterialTheme.typography.headlineLarge
                )
                Divider()
            }
            itemsIndexed(state.value.insumos) { index, item ->
                CardProduto(
                    modifier = Modifier.padding(10.dp),
                    produto = item,
                    onAddQtd = { viewModel.onEvent(ProdutoEvent.OnAddQtd(index = index)) },
                    onRemoverQtd = { viewModel.onEvent(ProdutoEvent.OnRemoveQtd(index = index)) },
                    onExcluirProduto = { viewModel.onEvent(ProdutoEvent.OnRemoveProduto(index = index)) })
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