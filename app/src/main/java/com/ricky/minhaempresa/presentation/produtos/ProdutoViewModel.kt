package com.ricky.minhaempresa.presentation.produtos

import android.util.Log
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
            is ProdutoEvent.OnAddQtd -> {
                val updateProdutos = _state.value.produtos.map { produto ->
                    if (produto.id == event.id) {
                        produto.copy(qtd = produto.qtd + 1)
                    } else {
                        produto
                    }
                }

                _state.update {
                    it.copy(
                        produtos = updateProdutos
                    )
                }

                viewModelScope.launch {
                    val produtoRecuperado = repository.getProdutoById(event.id)

                    produtoRecuperado.qtd += 1
                    repository.updateProduto(produtoRecuperado)
                }
                Log.i("infoteste", "onEvent: _state: ${_state.value.produtos}")
                Log.i("infoteste", "onEvent: state: ${state.value.produtos}")
            }

            is ProdutoEvent.OnRemoveQtd -> {
                val updateProdutos = _state.value.produtos.map { produto ->
                    if (produto.id == event.id) {
                        if (produto.qtd < 1) {
                            return
                        }
                        produto.copy(qtd = produto.qtd - 1)
                    } else {
                        produto
                    }
                }

                _state.update {
                    it.copy(
                        produtos = updateProdutos
                    )
                }

                viewModelScope.launch {
                    val produtoRecuperado = repository.getProdutoById(event.id)

                    produtoRecuperado.qtd -= 1
                    repository.updateProduto(produtoRecuperado)
                }

                Log.i("infoteste", "onEvent: _state: ${_state.value.produtos}")
                Log.i("infoteste", "onEvent: state: ${state.value.produtos}")
            }

            is ProdutoEvent.OnRemoveProduto -> {
                viewModelScope.launch {
                    repository.deleteProdutoById(_state.value.idProdutoDeletado)

                    _state.update {
                        it.copy(
                            idProdutoDeletado = "",
                            isShowDialog = false,
                        )
                    }
                }
            }

            ProdutoEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isShowDialog = !_state.value.isShowDialog
                    )
                }
            }

            is ProdutoEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        idProdutoDeletado = event.id,
                        isShowDialog = true,
                    )
                }
            }
        }
    }
}