package com.ricky.minhaempresa.data.converters

import androidx.room.TypeConverter
import com.ricky.minhaempresa.domain.model.ProdutoTipo
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun fromTipoProduto(tipo: ProdutoTipo): String {
        return tipo.name
    }

    @TypeConverter
    fun toTipoProduto(value: String): ProdutoTipo {
        return enumValueOf(value)
    }
}