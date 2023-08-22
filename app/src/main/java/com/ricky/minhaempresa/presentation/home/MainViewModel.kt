package com.ricky.minhaempresa.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.minhaempresa.common.DataStoreUtil
import com.ricky.minhaempresa.common.convertToString
import com.ricky.minhaempresa.domain.model.Balanco
import com.ricky.minhaempresa.domain.model.Produto
import com.ricky.minhaempresa.domain.repository.BalancoRepository
import com.ricky.minhaempresa.domain.repository.ProdutoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val produtoRepository: ProdutoRepository,
    private val balancoRepository: BalancoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getTheme().collect { isDarkMode ->
                _state.update {
                    it.copy(
                        darkMode = isDarkMode
                    )
                }
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnChangeTheme -> {
                _state.update {
                    it.copy(
                        darkMode = event.isDark
                    )
                }
                viewModelScope.launch {
                    dataStoreUtil.saveTheme(event.isDark)
                }
            }

            MainEvent.AddFaturamento -> {

                if (_state.value.nomeFaturamento.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorNomeFaturamento = true
                        )
                    }
                    return
                }
                val faturamento = Balanco(
                    nome = _state.value.nomeFaturamento.trim(),
                    entrada = if (_state.value.entrada.isBlank()) BigDecimal(0.0) else BigDecimal(
                        _state.value.entrada
                    ),
                    saida = if (_state.value.saida.isBlank()) BigDecimal(0.0) else BigDecimal(
                        _state.value.saida
                    ),
                    data = Date.from(Instant.now()).convertToString()
                )
                viewModelScope.launch {
                    balancoRepository.insertBalanco(faturamento)
                }
                _state.update {
                    it.copy(
                        isDialogShow = false,
                        nomeFaturamento = "",
                        entrada = "",
                        saida = ""
                    )
                }
            }

            MainEvent.AddProduto -> {
                if (_state.value.nome.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorNome = true
                        )
                    }
                    return
                }
                if (_state.value.medida.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorMedida = true
                        )
                    }
                    return
                }

                val produto = Produto(
                    nome = _state.value.nome,
                    medida = _state.value.medida,
                    tipo = _state.value.tipo
                )

                _state.update {
                    it.copy(
                        nome = "",
                        medida = "",
                        isDialogShow = false
                    )
                }
                viewModelScope.launch {
                    produtoRepository.insertProduto(produto)
                }
                _state.update {
                    it.copy(isDialogShow = false)
                }
            }

            MainEvent.ShowDialog -> {
                _state.update {
                    it.copy(isDialogShow = !_state.value.isDialogShow)
                }
            }

            is MainEvent.OnChangeMedida -> {
                _state.update {
                    it.copy(
                        medida = event.medida,
                        onErrorMedida = false
                    )
                }
            }

            is MainEvent.OnChangeNome -> {
                _state.update {
                    it.copy(
                        nome = event.nome,
                        onErrorMedida = false
                    )
                }
            }

            is MainEvent.OnChangeTipo -> {
                _state.update {
                    it.copy(
                        tipo = event.tipo
                    )
                }
            }

            is MainEvent.IsTelaProduto -> {
                _state.update {
                    it.copy(
                        isProdutos = event.isProduto
                    )
                }
            }

            is MainEvent.OnChangeEntrada -> {
                _state.update {
                    it.copy(entrada = event.entrada)
                }
            }

            is MainEvent.OnChangeSaida -> {
                _state.update {
                    it.copy(
                        saida = event.saida
                    )
                }
            }

            is MainEvent.OnChangeNomeFaturamento -> {
                _state.update {
                    it.copy(
                        onErrorNomeFaturamento = false,
                        nomeFaturamento = event.nome
                    )
                }
            }
        }
    }
}