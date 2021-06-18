package br.com.zup.nats.core.model

import java.math.BigDecimal
import java.util.*

data class Produto(
    val id: UUID? = null,
    val nome: String = "",
    val categoria: String = "",
    val preco: BigDecimal = BigDecimal.ZERO
)
