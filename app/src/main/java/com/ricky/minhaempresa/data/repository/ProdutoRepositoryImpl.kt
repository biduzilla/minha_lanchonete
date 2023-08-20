package com.ricky.minhaempresa.data.repository

import com.ricky.minhaempresa.data.Database
import com.ricky.minhaempresa.domain.model.Produto
import com.ricky.minhaempresa.domain.repository.ProdutoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProdutoRepositoryImpl @Inject constructor(private val dao: Database) : ProdutoRepository {
    override suspend fun getAllProdutos(): Flow<List<Produto>> = dao.produtoDao().getAllProdutos()
    override suspend fun insertProduto(produto: Produto) = dao.produtoDao().insertProduto(produto)
    override suspend fun updateProduto(produto: Produto) = dao.produtoDao().updateProduto(produto)
    override suspend fun deleteProduto(produto: Produto) = dao.produtoDao().deleteProduto(produto)

}