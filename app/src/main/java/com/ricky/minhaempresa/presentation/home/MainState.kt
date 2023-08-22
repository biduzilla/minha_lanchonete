package com.ricky.minhaempresa.presentation.home

import com.ricky.minhaempresa.domain.model.ProdutoTipo
import java.math.BigDecimal

data class MainState(
    val darkMode: Boolean = false,
    val isProdutos: Boolean = true,
    val nome: String = "",
    val nomeFaturamento: String = "",
    val medida: String = "",
    val entrada: String = "",
    val saida: String = "",
    val tipo: ProdutoTipo = ProdutoTipo.BEBIDA,
    val isDialogShow: Boolean = false,
    val onErrorNome: Boolean = false,
    val onErrorMedida: Boolean = false,
    val onErrorNomeFaturamento: Boolean = false,
)