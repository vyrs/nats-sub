package br.com.zup.nats.database.service

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.ProdutoDatabaseServicePort
import br.com.zup.nats.database.model.ProdutoEntity
import br.com.zup.nats.database.repository.ProdutoRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class ProdutoDatabaseService(private val produtoRepository: ProdutoRepository): ProdutoDatabaseServicePort {
    override fun save(produtoEntity: ProdutoEntity): Produto {
        val result = produtoRepository.saveCql(produtoEntity)
        return ProdutoConverter.produtoEntityToProduto(result)
    }

    override fun update(produtoEntity: ProdutoEntity): Produto {
        val result = produtoRepository.updateCql(produtoEntity)
        return ProdutoConverter.produtoEntityToProduto(result)
    }

    override fun delete(id: UUID) {
        produtoRepository.deletaCql(id)
    }
}