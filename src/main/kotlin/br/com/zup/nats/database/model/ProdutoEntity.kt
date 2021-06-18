package br.com.zup.nats.database.model

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.util.*

@Introspected
data class ProdutoEntity(
    val id: UUID? = null,
    val nome: String = "",
    val categoria: String = "",
    val preco: BigDecimal = BigDecimal.ZERO
)