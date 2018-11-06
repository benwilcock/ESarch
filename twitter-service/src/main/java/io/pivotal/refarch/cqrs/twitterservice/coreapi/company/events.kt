package io.pivotal.refarch.cqrs.twitterservice.coreapi.company

import io.pivotal.refarch.cqrs.twitterservice.coreapi.orders.OrderBookId

data class CompanyCreatedEvent(
        val companyId: CompanyId,
        val companyName: String,
        val companyValue: Long,
        val amountOfShares: Long
)

data class OrderBookAddedToCompanyEvent(
        val companyId: CompanyId,
        val orderBookId: OrderBookId
)
