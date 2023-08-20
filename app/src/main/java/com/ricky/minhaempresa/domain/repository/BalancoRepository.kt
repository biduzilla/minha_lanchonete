package com.ricky.minhaempresa.domain.repository

import com.ricky.minhaempresa.domain.model.Balanco
import kotlinx.coroutines.flow.Flow

interface BalancoRepository {

    suspend fun getAllBalanco(): Flow<List<Balanco>>
    suspend fun insertBalanco(balanco: Balanco)
    suspend fun getRegistrosDoMesEAno(mes: String, ano: String): Flow<List<Balanco>>
    suspend fun getBalancosDaSemana(dataSeteDiasAtras: String, dataHoje: String): Flow<List<Balanco>>
}