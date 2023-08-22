package com.ricky.minhaempresa.presentation.produtos.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.minhaempresa.domain.model.Produto
import com.ricky.minhaempresa.domain.model.ProdutoTipo
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme

@Composable
fun CardProduto(
    modifier: Modifier = Modifier,
    produto: Produto,
    onAddQtd: () -> Unit,
    onRemoverQtd: () -> Unit,
    onExcluirProduto: () -> Unit,
) {
//    var qtd by remember { mutableStateOf(produto.qtd) }
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .size(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = produto.nome,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                IconButton(
                    onClick = { onExcluirProduto() }, modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        IconButton(onClick = {
                            onAddQtd()
                        }) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "${produto.qtd} ${produto.medida}",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        IconButton(onClick = {
                            onRemoverQtd()
                        }) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Icons.Default.Remove,
                                contentDescription = null
                            )
                        }
                    }
                }
                Box(
                    Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .clip(RoundedCornerShape(20.dp))

                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                            .padding(16.dp),
                        imageVector = if (produto.tipo == ProdutoTipo.BEBIDA) Icons.Default.LocalBar
                        else Icons.Default.LunchDining,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardProdutoPreview() {
    MinhaEmpresaTheme {
        CardProduto(
            produto = Produto(
                nome = "nome",
                qtd = 5,
                medida = "litros",
                tipo = ProdutoTipo.BEBIDA
            ),
            onAddQtd = {},
            onExcluirProduto = {},
            onRemoverQtd = {}
        )
    }
}