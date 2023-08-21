package com.ricky.minhaempresa.presentation.produtos

sealed interface ProdutoEvent {
    data class OnAtualizarProdutoQtd(val id: String, val qtd:Int) : ProdutoEvent
    data class OnRemoveProduto(val id: String) : ProdutoEvent
}