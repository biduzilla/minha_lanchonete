package com.ricky.minhaempresa.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Produto(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var nome: String = "",
    var qtd: Int = 0,
    var medida: String = "",
    var tipo: ProdutoTipo = ProdutoTipo.BEBIDA
)
