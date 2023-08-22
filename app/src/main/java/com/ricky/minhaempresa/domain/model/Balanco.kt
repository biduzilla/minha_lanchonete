package com.ricky.minhaempresa.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Balanco(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var nome:String = "",
    var entrada: BigDecimal = BigDecimal(0.0),
    var saida: BigDecimal = BigDecimal(0.0),
    var data: String
)
