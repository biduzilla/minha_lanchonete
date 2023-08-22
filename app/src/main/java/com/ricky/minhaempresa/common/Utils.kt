package com.ricky.minhaempresa.common

import androidx.compose.ui.text.capitalize
import com.ricky.minhaempresa.domain.model.FaturamentoMesAno
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Locale


fun formatarListaMesAno(faturamentos: List<FaturamentoMesAno>): List<FaturamentoMesAno> {
    return faturamentos.map { faturamento ->
        val mesAnoParts = faturamento.mesAno.split("/")
        val mesNum = mesAnoParts[0].toInt()
        val ano = mesAnoParts[1].toInt()

        val monthName = DateFormatSymbols.getInstance(Locale("pt", "BR")).months[mesNum - 1]

        val mesAnoFormatado = "${capitalizeFirstLetter(monthName)} de $ano"

        faturamento.copy(mesAno = mesAnoFormatado)
    }
}

fun capitalizeFirstLetter(input: String): String {
    if (input.isEmpty()) {
        return input
    }
    return input.substring(0, 1).toUpperCase() + input.substring(1)
}
