package com.ricky.minhaempresa.domain.model

import java.math.BigDecimal

data class FaturamentoMesAno(
    var faturamentos: List<Balanco> = emptyList(),
    var mesAno: String = "",
    var totalEntrada: BigDecimal = BigDecimal(0.0),
    var totalSaida: BigDecimal = BigDecimal(0.0),
)
