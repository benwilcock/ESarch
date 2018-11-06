package io.pivotal.refarch.cqrs.twitterservice.query;

import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("trade-tweets")
public class TradeEventHandler {

    private final TwitterService twitterService;

    public TradeEventHandler(TwitterService twitterService) {
        this.twitterService = twitterService;
    }
}
