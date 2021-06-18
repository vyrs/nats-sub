package br.com.zup.nats.infrastructure.subscribe

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.infrastructure.model.Eventos
import br.com.zup.nats.infrastructure.model.EventsInformation
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

@MicronautTest
class ProdutoServerTest: AnnotationSpec() {
    val service = mockk<ProdutoServicePort>(relaxed = true)

    val produtoServer = ProdutoServer(service)

    lateinit var produtoEvent: ProdutoEvent
    lateinit var eventsInformationSave: EventsInformation

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        produtoEvent = ProdutoEvent(nome = "galaxy pocket", categoria = "celular", preco = BigDecimal.TEN)
        eventsInformationSave = EventsInformation(Eventos.SAVE_PRODUCT, produtoEvent)
    }

    @Test
    fun `deve receber EventsInformation do tipo save e deve salvar o produto`() {
        every { service.insert(any()) } returns produtoEvent

        val result = produtoServer.receive(eventsInformationSave)

        result shouldBe Unit
    }

}
