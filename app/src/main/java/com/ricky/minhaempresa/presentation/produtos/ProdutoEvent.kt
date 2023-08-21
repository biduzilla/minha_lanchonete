package com.ricky.minhaempresa.presentation.produtos

sealed interface ProdutoEvent {
    data class OnAddQtd(val index: Int) : ProdutoEvent
    data class OnRemoveQtd(val index: Int) : ProdutoEvent
    data class OnRemoveProduto(val index: Int) : ProdutoEvent
}