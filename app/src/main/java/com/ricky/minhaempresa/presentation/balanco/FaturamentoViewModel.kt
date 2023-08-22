package com.ricky.minhaempresa.presentation.balanco

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.minhaempresa.common.formatarListaMesAno
import com.ricky.minhaempresa.domain.model.FaturamentoMesAno
import com.ricky.minhaempresa.domain.repository.BalancoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaturamentoViewModel @Inject constructor(private val repository: BalancoRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(FaturamentoState())
    val state = _state.asStateFlow()

    init {
        recuperaFaturamentos()
    }

    private fun recuperaFaturamentos() {
        viewModelScope.launch {
            repository.getAllBalanco().collect { faturamentos ->
                val balancosPorMesAno = faturamentos.groupBy { it.data.substring(3) }
                val faturamentosMesAno = balancosPorMesAno.map { (mesAno, balancos) ->
                    val totalEntrada = balancos.sumOf { it.entrada }
                    val totalSaida = balancos.sumOf { it.saida }
                    FaturamentoMesAno(
                        faturamentos = balancos,
                        mesAno = mesAno,
                        totalEntrada = totalEntrada,
                        totalSaida = totalSaida
                    )
                }
                _state.update {
                    it.copy(
                        faturamentos = formatarListaMesAno(faturamentosMesAno)
                    )
                }
            }
        }
    }
}