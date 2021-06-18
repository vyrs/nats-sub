package br.com.zup.nats.core.ports

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import java.util.*
import javax.inject.Singleton

@Singleton
interface ProdutoServicePort {
    fun insert(produto: Produto): ProdutoEvent
    fun update(produto: Produto): ProdutoEvent
    fun delete(id: UUID)
}