package com.ricky.minhaempresa.presentation.produtos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.minhaempresa.domain.model.Produto
import com.ricky.minhaempresa.domain.model.ProdutoTipo
import com.ricky.minhaempresa.domain.repository.ProdutoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdutoViewModel @Inject constructor(private val repository: ProdutoRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(ProdutosState())
    val state = _state.asStateFlow()
    private var produtos = mutableListOf<Produto>()

    init {
        observarProdutos()
    }

    private fun observarProdutos() {
        viewModelScope.launch {
            repository.getAllProdutos().collect { produtos ->
                _state.value = _state.value.copy(
                    bebidas = produtos.filter { it.tipo == ProdutoTipo.BEBIDA },
                    insumos = produtos.filter { it.tipo == ProdutoTipo.LANCHE },
                    produtos = produtos
                )
            }
        }
    }

    fun onEvent(event: ProdutoEvent) {
        when (event) {
            is ProdutoEvent.OnAddQtd -> {
                _state.value.produtos[event.index].qtd += 1

                viewModelScope.launch {
                    repository.updateProduto(_state.value.produtos[event.index])
                }
            }

            is ProdutoEvent.OnRemoveProduto -> {
                viewModelScope.launch {
                    repository.deleteProduto(_state.value.produtos[event.index])
                }
            }

            is ProdutoEvent.OnRemoveQtd -> {
                if (_state.value.produtos[event.index].qtd > 0) {
                    _state.value.produtos[event.index].qtd -= 1

                    viewModelScope.launch {
                        repository.updateProduto(_state.value.produtos[event.index])
                    }
                }
            }
        }
    }
}