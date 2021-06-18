package br.com.zup.nats.core.ports

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.database.model.ProdutoEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface ProdutoDatabaseServicePort {
    fun save(produtoEntity: ProdutoEntity): Produto
    fun update(produtoEntity: ProdutoEntity): Produto
    fun delete(id: UUID)
}