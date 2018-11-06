package io.pivotal.refarch.cqrs.twitterservice;

import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.SubscribableMessageSource;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AmqpConfiguration {

    @Autowired
    public void defineExchange(AmqpAdmin admin) {
        Queue tradesQueue = QueueBuilder.durable("twitter-service").build();
        Exchange tradingExchange = ExchangeBuilder.topicExchange("trading-engine-events").build();

        admin.declareExchange(tradingExchange);
        admin.declareQueue(tradesQueue);
        admin.declareBinding(BindingBuilder.bind(tradesQueue)
                                           .to(tradingExchange)
                                           .with("#")
                                           .noargs());
    }

    @Autowired
    public void config(EventProcessingConfiguration epConfig,
                       @Qualifier("trade-events") SubscribableMessageSource<EventMessage<?>> tradeEvents) {
        epConfig.registerSubscribingEventProcessor("trade-tweets", c -> tradeEvents);
    }

    @Qualifier("trade-events")
    @Bean
    public SubscribableMessageSource<EventMessage<?>> tradeEvents(AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {
            @Transactional
            @RabbitListener(queues = "twitter-service")
            @Override
            public void onMessage(Message message, Channel channel) {
                super.onMessage(message, channel);
            }
        };
    }
}
