package com.ricky.minhaempresa.data.repository

import com.ricky.minhaempresa.data.Database
import com.ricky.minhaempresa.domain.model.Balanco
import com.ricky.minhaempresa.domain.repository.BalancoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BalancoRepositoryImpl @Inject constructor(private val dao: Database) : BalancoRepository {
    override suspend fun getAllBalanco(): Flow<List<Balanco>> = dao.balancoDao().getAllBalanco()

    override suspend fun insertBalanco(balanco: Balanco) = dao.balancoDao().insertBalanco(balanco)

    override suspend fun getRegistrosDoMesEAno(mes: String, ano: String): Flow<List<Balanco>> {
        return dao.balancoDao().getRegistrosDoMesEAno(mes = mes, ano = ano)
    }

    override suspend fun getBalancosDaSemana(
        dataSeteDiasAtras: String,
        dataHoje: String
    ): Flow<List<Balanco>> {
        return dao.balancoDao().getBalancosDaSemana(
            dataSeteDiasAtras = dataSeteDiasAtras,
            dataHoje = dataHoje
        )
    }


}