package com.ricky.minhaempresa.presentation.produtos

sealed interface ProdutoEvent {
    data class OnAddQtd(val id: String) : ProdutoEvent
    data class OnRemoveQtd(val id: String) : ProdutoEvent
    data class ShowDialog(val id: String) : ProdutoEvent
    object HideDialog : ProdutoEvent
    object OnRemoveProduto : ProdutoEvent
}