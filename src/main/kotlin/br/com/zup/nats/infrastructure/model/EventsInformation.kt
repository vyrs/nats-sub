package br.com.zup.nats.infrastructure.model

data class EventsInformation(
    val event: Eventos = Eventos.UNKNOWN,
    val produto: ProdutoEvent = ProdutoEvent()
)
