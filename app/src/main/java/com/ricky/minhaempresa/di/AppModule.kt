package com.ricky.minhaempresa.di

import android.content.Context
import androidx.room.Room
import com.ricky.minhaempresa.data.Database
import com.ricky.minhaempresa.data.dao.BalancoDao
import com.ricky.minhaempresa.data.dao.ProdutoDao
import com.ricky.minhaempresa.data.repository.BalancoRepositoryImpl
import com.ricky.minhaempresa.data.repository.ProdutoRepositoryImpl
import com.ricky.minhaempresa.domain.repository.BalancoRepository
import com.ricky.minhaempresa.domain.repository.ProdutoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProdutoDao(database: Database): ProdutoDao = database.produtoDao()

    @Singleton
    @Provides
    fun provideBalancoDao(database: Database): BalancoDao = database.balancoDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "app_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProdutoRepository(database: Database): ProdutoRepository {
        return ProdutoRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideBalancoRepository(database: Database): BalancoRepository {
        return BalancoRepositoryImpl(database)
    }
}