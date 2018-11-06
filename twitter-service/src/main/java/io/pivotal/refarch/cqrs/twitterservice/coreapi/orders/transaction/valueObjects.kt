package io.pivotal.refarch.cqrs.twitterservice.coreapi.orders.transaction

enum class TransactionState {
    STARTED, CONFIRMED, CANCELLED, EXECUTED, PARTIALLY_EXECUTED
}
