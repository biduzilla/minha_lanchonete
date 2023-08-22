package com.ricky.minhaempresa.presentation.balanco

import com.ricky.minhaempresa.domain.model.Balanco

data class FaturamentoState(
    val faturamentos: List<Balanco> = emptyList()
)