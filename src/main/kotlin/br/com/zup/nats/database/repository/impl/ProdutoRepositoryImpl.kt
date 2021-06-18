package br.com.zup.nats.database.repository.impl

import br.com.zup.nats.database.model.ProdutoEntity
import br.com.zup.nats.database.repository.ProdutoRepository
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class ProdutoRepositoryImpl(private val cqlSession: CqlSession): ProdutoRepository {

    val logger = LoggerFactory.getLogger(this::class.java)


    override fun saveCql(produtoEntity: ProdutoEntity): ProdutoEntity {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "INSERT INTO produto(id, nome, categoria, preco) VALUES (?, ?, ?, ?)",
                    UUID.randomUUID(),
                    produtoEntity.nome,
                    produtoEntity.categoria,
                    produtoEntity.preco
                )
        )
        logger.info("Salvando produto recebido!")
        return produtoEntity
    }

    override fun updateCql(produtoEntity: ProdutoEntity): ProdutoEntity {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "UPDATE produto SET nome = ?, categoria = ?, preco = ? WHERE id = ? IF EXISTS",
                    produtoEntity.nome,
                    produtoEntity.categoria,
                    produtoEntity.preco,
                    produtoEntity.id
                )
        )
        logger.info("Atualizando produto ${produtoEntity.id}")
        return produtoEntity
    }

    override fun deletaCql(id: UUID) {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "DELETE FROM produto WHERE id = ? IF EXISTS",
                    id
                )
        )
        logger.info("Deletando produto $id")
    }
}