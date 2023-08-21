package com.ricky.minhaempresa.presentation.produtos

import com.ricky.minhaempresa.domain.model.Produto

data class ProdutosState(
    val bebidas: List<Produto> = emptyList(),
    val insumos: List<Produto> = emptyList(),
    val produtos: List<Produto> = emptyList()
)