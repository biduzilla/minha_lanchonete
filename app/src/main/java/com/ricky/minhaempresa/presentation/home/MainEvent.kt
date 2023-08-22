package com.ricky.minhaempresa.presentation.home

import com.ricky.minhaempresa.domain.model.ProdutoTipo

sealed interface MainEvent {
    data class OnChangeTheme(val isDark: Boolean) : MainEvent
    data class IsTelaProduto(val isProduto: Boolean) : MainEvent
    data class OnChangeNome(val nome: String) : MainEvent
    data class OnChangeTipo(val tipo: ProdutoTipo) : MainEvent
    data class OnChangeMedida(val medida: String) : MainEvent
    data class OnChangeEntrada(val entrada: String) : MainEvent
    data class OnChangeSaida(val saida: String) : MainEvent
    data class OnChangeNomeFaturamento(val nome: String) : MainEvent
    object AddProduto : MainEvent
    object AddFaturamento : MainEvent
    object ShowDialog : MainEvent
}