package br.com.zup.nats.core.service

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.ProdutoDatabaseServicePort
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.infrastructure.model.Eventos
import br.com.zup.nats.infrastructure.model.EventsInformation
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

@MicronautTest
class ProdutoServiceTest: AnnotationSpec() {
    val serviceRepository = mockk<ProdutoDatabaseServicePort>(relaxed = true)

    val produtoService = ProdutoService(serviceRepository)

    lateinit var produtoEvent: ProdutoEvent
    lateinit var produto: Produto

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        produtoEvent = ProdutoEvent(nome = "galaxy pocket", categoria = "celular", preco = BigDecimal.TEN)
        produto = Produto(null, "galaxy pocket", "celular", BigDecimal.TEN)
    }

    @Test
    fun `deve receber e salvar produto e retornar um ProdutoEvent`() {
        every { serviceRepository.save(any()) } returns produto

        val result = produtoService.insert(produto)

        result shouldBe produtoEvent
    }

    @Test
    fun `deve receber e atualizar produto e retornar um ProdutoEvent`() {

        val produto2 = Produto(UUID.randomUUID(), "celular", "celular", BigDecimal.TEN)
        val produtoEvent2 = ProdutoEvent(produto2.id, produto2.nome, produto2.categoria, produto2.preco)

        every { serviceRepository.update(any()) } returns produto2

        val result = produtoService.update(produto2)

        result shouldBe produtoEvent2
    }

}
