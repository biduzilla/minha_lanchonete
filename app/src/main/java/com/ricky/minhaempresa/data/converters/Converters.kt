package com.ricky.minhaempresa.data.converters

import androidx.room.TypeConverter
import com.ricky.minhaempresa.domain.model.ProdutoTipo

class Converters {

    @TypeConverter
    fun fromTipoProduto(tipo: ProdutoTipo): String {
        return tipo.name
    }

    @TypeConverter
    fun toTipoProduto(value: String): ProdutoTipo {
        return enumValueOf(value)
    }
}