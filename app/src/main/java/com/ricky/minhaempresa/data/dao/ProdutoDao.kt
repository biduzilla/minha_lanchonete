package com.ricky.minhaempresa.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.minhaempresa.domain.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM PRODUTO")
    fun getAllProdutos(): Flow<List<Produto>>

    @Query("SELECT * FROM PRODUTO WHERE id = :productId")
    suspend fun getProdutoById(productId: String): Produto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduto(produto: Produto)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduto(produto: Produto)

    @Delete
    suspend fun deleteProduto(produto: Produto)

    @Query("DELETE FROM PRODUTO WHERE id = :productId")
    suspend fun deleteProdutoById(productId: String)
}