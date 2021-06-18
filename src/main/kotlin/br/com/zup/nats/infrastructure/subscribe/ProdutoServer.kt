package br.com.zup.nats.infrastructure.subscribe

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.infrastructure.model.Eventos
import br.com.zup.nats.infrastructure.model.EventsInformation
import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject

@NatsListener
class ProdutoServer(private val produtoService: ProdutoServicePort) {

    @Subject("produto")
    fun receive(eventsInformation: EventsInformation) {
        when(eventsInformation.event) {
            Eventos.SAVE_PRODUCT -> produtoService.insert(
                ProdutoConverter.produtoEventToProduto(
                    eventsInformation.produto
                )
            )
            Eventos.UPDATE_PRODUCT -> produtoService.update(
                ProdutoConverter.produtoEventToProduto(
                    eventsInformation.produto
                )
            )
            Eventos.DELETE_PRODUCT -> produtoService.delete(eventsInformation.produto.id!!)
        }

    }
}
