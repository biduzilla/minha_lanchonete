package com.ricky.minhaempresa.presentation.home

import com.ricky.minhaempresa.domain.model.ProdutoTipo

data class MainState(
    val darkMode: Boolean = false,
    val isProdutos: Boolean = true,
    val nome: String = "",
    val medida: String = "",
    val tipo: ProdutoTipo = ProdutoTipo.BEBIDA,
    val isDialogShow: Boolean = false,
    val onErrorNome: Boolean = false,
    val onErrorMedida: Boolean = false,
)