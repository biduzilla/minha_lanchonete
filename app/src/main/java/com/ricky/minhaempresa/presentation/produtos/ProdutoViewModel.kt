package com.ricky.minhaempresa.presentation.produtos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.minhaempresa.domain.repository.ProdutoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdutoViewModel @Inject constructor(private val repository: ProdutoRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(ProdutosState())
    val state = _state.asStateFlow()

    init {
        observarProdutos()
    }

    private fun observarProdutos() {
        viewModelScope.launch {
            repository.getAllProdutos().collect { produtos ->
                _state.value = _state.value.copy(
                    produtos = produtos
                )
            }
        }
    }

    fun onEvent(event: ProdutoEvent) {
        when (event) {
            is ProdutoEvent.OnRemoveProduto -> {

            }

            is ProdutoEvent.OnAtualizarProdutoQtd -> {
                val updatedProdutos = _state.value.produtos.toMutableList()
                updatedProdutos.filter { it.id == event.id }
                updatedProdutos[0].qtd = event.qtd

                _state.value = _state.value.copy(produtos = updatedProdutos)

                _state.update {
                    it.copy(produtos = updatedProdutos)
                }

                viewModelScope.launch {
                    repository.updateProduto(updatedProdutos[0])
                }
            }
        }
    }
}