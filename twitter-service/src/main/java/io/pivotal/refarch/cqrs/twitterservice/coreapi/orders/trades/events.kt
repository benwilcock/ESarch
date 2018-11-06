package io.pivotal.refarch.cqrs.twitterservice.coreapi.orders.trades

import io.pivotal.refarch.cqrs.twitterservice.coreapi.orders.OrderBookId
import io.pivotal.refarch.cqrs.twitterservice.coreapi.orders.transaction.TransactionId
import io.pivotal.refarch.cqrs.twitterservice.coreapi.portfolio.PortfolioId
import java.io.Serializable

abstract class AbstractOrderPlacedEvent(
        open val orderBookId: OrderBookId,
        open val orderId: OrderId,
        open val transactionId: TransactionId,
        open val tradeCount: Long,
        open val itemPrice: Long,
        open val portfolioId: PortfolioId
)

data class BuyOrderPlacedEvent(
        override val orderBookId: OrderBookId,
        override val orderId: OrderId,
        override val transactionId: TransactionId,
        override val tradeCount: Long,
        override val itemPrice: Long,
        override val portfolioId: PortfolioId
) : AbstractOrderPlacedEvent(orderBookId, orderId, transactionId, tradeCount, itemPrice, portfolioId)

data class SellOrderPlacedEvent(
        override val orderBookId: OrderBookId,
        override val orderId: OrderId,
        override val transactionId: TransactionId,
        override val tradeCount: Long,
        override val itemPrice: Long,
        override val portfolioId: PortfolioId
) : AbstractOrderPlacedEvent(orderBookId, orderId, transactionId, tradeCount, itemPrice, portfolioId)

abstract class OrderBookEvent(open val orderBookId: OrderBookId)

data class OrderBookCreatedEvent(override val orderBookId: OrderBookId) : OrderBookEvent(orderBookId)

data class TradeExecutedEvent(
        val orderBookId: OrderBookId,
        val tradeCount: Long,
        val tradePrice: Long,
        val buyOrderId: OrderId,
        val sellOrderId: OrderId,
        val buyTransactionId: TransactionId,
        val sellTransactionId: TransactionId
) : Serializable {
    companion object {
        private const val serialVersionUID = 6292249351659536792L
    }
}
