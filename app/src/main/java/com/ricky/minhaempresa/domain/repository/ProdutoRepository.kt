package com.ricky.minhaempresa.domain.repository

import com.ricky.minhaempresa.domain.model.Produto
import kotlinx.coroutines.flow.Flow

interface ProdutoRepository {

    suspend fun getAllProdutos(): Flow<List<Produto>>
    suspend fun getProdutoById(id: String): Produto
    suspend fun insertProduto(produto: Produto)
    suspend fun updateProduto(produto: Produto)
    suspend fun deleteProduto(produto: Produto)
    suspend fun deleteProdutoById(produtoId: String)
}