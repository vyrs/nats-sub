package br.com.zup.nats.infrastructure.model

enum class Eventos(val event: String) {
    UNKNOWN("UNKNOWN"),
    SAVE_PRODUCT("SAVE_PRODUCT"),
    UPDATE_PRODUCT("UPDATE_PRODUCT"),
    DELETE_PRODUCT("DELETE_PRODUCT"),
}