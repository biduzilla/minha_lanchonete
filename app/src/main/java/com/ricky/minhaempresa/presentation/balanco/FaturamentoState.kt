package com.ricky.minhaempresa.presentation.balanco

import com.ricky.minhaempresa.domain.model.FaturamentoMesAno

data class FaturamentoState(
    val faturamentos: List<FaturamentoMesAno> = emptyList()
)