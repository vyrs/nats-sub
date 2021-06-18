package br.com.zup.nats.core.mapper

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.database.model.ProdutoEntity
import br.com.zup.nats.infrastructure.model.ProdutoEvent

class ProdutoConverter {
    companion object {
        fun produtoEventToProduto(produtoEvent: ProdutoEvent) =
            Produto(produtoEvent.id, produtoEvent.nome, produtoEvent.categoria, produtoEvent.preco)

        fun produtoToProdutoEvent(produto: Produto) =
            ProdutoEvent(produto.id, produto.nome, produto.categoria, produto.preco)

        fun produtoToProdutoEntity(produto: Produto) =
            ProdutoEntity(produto.id, produto.nome, produto.categoria, produto.preco)

        fun produtoEntityToProduto(produtoEntity: ProdutoEntity) =
            Produto(produtoEntity.id, produtoEntity.nome, produtoEntity.categoria, produtoEntity.preco)
    }
}