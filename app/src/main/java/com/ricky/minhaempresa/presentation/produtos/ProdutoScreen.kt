package com.ricky.minhaempresa.presentation.produtos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@Composable
fun ProdutoScreen(viewModel: ProdutoViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Box {
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
                    onAtualizarQtd = { qtd ->
                        viewModel.onEvent(
                            ProdutoEvent.OnAtualizarProdutoQtd(
                                id = item.id,
                                qtd = qtd
                            )
                        )
                    },
                    onExcluirProduto = { viewModel.onEvent(ProdutoEvent.OnRemoveProduto(id = item.id)) })
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
                    onAtualizarQtd = { qtd ->
                        viewModel.onEvent(
                            ProdutoEvent.OnAtualizarProdutoQtd(
                                id = item.id,
                                qtd = qtd
                            )
                        )
                    },
                    onExcluirProduto = { viewModel.onEvent(ProdutoEvent.OnRemoveProduto(id = item.id)) })

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