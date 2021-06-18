package br.com.zup.nats.database.repository

import br.com.zup.nats.database.model.ProdutoEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface ProdutoRepository {
    fun saveCql(produtoEntity: ProdutoEntity): ProdutoEntity
    fun updateCql(produtoEntity: ProdutoEntity): ProdutoEntity
    fun deletaCql(id: UUID)
}