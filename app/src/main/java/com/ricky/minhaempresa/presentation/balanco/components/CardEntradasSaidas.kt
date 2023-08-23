package com.ricky.minhaempresa.presentation.balanco.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.minhaempresa.domain.model.Balanco
import com.ricky.minhaempresa.ui.theme.MinhaEmpresaTheme
import java.math.BigDecimal

@Composable
fun CardEntradasSaidas(
    modifier: Modifier = Modifier,
    balanco: Balanco
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = if (balanco.entrada == BigDecimal(0.0)) "Entrada = R$0.0" else "Entrada = R$${balanco.entrada}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (balanco.saida == BigDecimal(0.0)) "Saída = R$0.0" else "Saída = R$${balanco.entrada}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red
            )
        }
        Text(
            text = balanco.nome,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Preview
@Composable
private fun CardEntradasSaidasPreview() {
    MinhaEmpresaTheme {
        CardEntradasSaidas(
            balanco = Balanco(
                nome = "teste",
                entrada = BigDecimal("54.1"),
                saida = BigDecimal("54.1"),
                data = "24/11/1992"
            )
        )
    }
}