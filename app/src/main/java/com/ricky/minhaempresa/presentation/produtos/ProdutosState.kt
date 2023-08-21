package com.ricky.minhaempresa.presentation.produtos

import com.ricky.minhaempresa.domain.model.Produto

data class ProdutosState(
    val produtos: List<Produto> = emptyList(),
    val isShowDialog:Boolean = false,
    val idProdutoDeletado:String = ""
)