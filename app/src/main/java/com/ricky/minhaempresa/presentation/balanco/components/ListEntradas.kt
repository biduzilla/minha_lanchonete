package com.ricky.minhaempresa.presentation.balanco.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ricky.minhaempresa.domain.model.FaturamentoMesAno
import java.math.BigDecimal

@Composable
fun ListViewEntradas(
    modifier: Modifier = Modifier,
    faturamentos: FaturamentoMesAno
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ), shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(modifier = modifier) {
            Text(
                text = faturamentos.mesAno,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            faturamentos.faturamentos.forEach { balanco ->
                CardEntradasSaidas(
                    balanco = balanco,
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 16.dp
                    )
                )
            }

            Divider(color = MaterialTheme.colorScheme.onTertiaryContainer, modifier = Modifier.padding(horizontal = 16.dp))

            Column(
                Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
            ) {
                Text(
                    text = if (faturamentos.totalEntrada == BigDecimal(0.0)) "Não teve entradas" else "Total Entradas = R$${faturamentos.totalEntrada}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Green
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (faturamentos.totalSaida == BigDecimal(0.0)) "Não teve saídas" else "Total Saídas = R$${faturamentos.totalSaida}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
            }
        }
    }
}