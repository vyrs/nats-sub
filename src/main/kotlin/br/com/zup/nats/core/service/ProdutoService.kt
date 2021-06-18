package br.com.zup.nats.core.service

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.ProdutoDatabaseServicePort
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import java.util.*
import javax.inject.Singleton

@Singleton
class ProdutoService(private val produtoDatabaseService: ProdutoDatabaseServicePort): ProdutoServicePort {
    override fun insert(produto: Produto): ProdutoEvent {
        return ProdutoConverter.produtoToProdutoEvent(
            produtoDatabaseService.save(
                ProdutoConverter.produtoToProdutoEntity(produto)
            )
        )
    }

    override fun update(produto: Produto): ProdutoEvent {
        return ProdutoConverter.produtoToProdutoEvent(
            produtoDatabaseService.update(
                ProdutoConverter.produtoToProdutoEntity(produto)
            )
        )
    }

    override fun delete(id: UUID) {
        produtoDatabaseService.delete(id)
    }
}