package io.pivotal.refarch.cqrs.twitterservice.coreapi.portfolio

import io.pivotal.refarch.cqrs.twitterservice.coreapi.users.UserId

abstract class PortfolioEvent(open val portfolioId: PortfolioId)

class PortfolioCreatedEvent(
        override val portfolioId: PortfolioId,
        val userId: UserId
) : PortfolioEvent(portfolioId)
