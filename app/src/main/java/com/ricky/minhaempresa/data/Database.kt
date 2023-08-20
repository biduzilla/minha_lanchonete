package com.ricky.minhaempresa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ricky.minhaempresa.data.converters.Converters
import com.ricky.minhaempresa.data.dao.BalancoDao
import com.ricky.minhaempresa.data.dao.ProdutoDao
import com.ricky.minhaempresa.domain.model.Balanco
import com.ricky.minhaempresa.domain.model.Produto

@Database(
    entities = [
        Produto::class,
        Balanco::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database:RoomDatabase() {

    abstract fun produtoDao():ProdutoDao
    abstract fun balancoDao():BalancoDao
}