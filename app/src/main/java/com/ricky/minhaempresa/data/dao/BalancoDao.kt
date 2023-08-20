package com.ricky.minhaempresa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ricky.minhaempresa.domain.model.Balanco
import kotlinx.coroutines.flow.Flow

@Dao
interface BalancoDao {

    @Query("SELECT * FROM BALANCO")
    suspend fun getAllBalanco(): Flow<List<Balanco>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalanco(balanco: Balanco)
    @Query("SELECT * FROM Balanco WHERE SUBSTR(data, 4, 2) = :mes AND SUBSTR(data, 7, 4) = :ano")
    suspend fun getRegistrosDoMesEAno(mes: String, ano: String): Flow<List<Balanco>>
    @Query("SELECT * FROM Balanco WHERE data BETWEEN :dataSeteDiasAtras AND :dataHoje")
    suspend fun getBalancosDaSemana(dataSeteDiasAtras: String, dataHoje: String): Flow<List<Balanco>>

}