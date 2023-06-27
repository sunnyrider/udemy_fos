package com.food.ordering.system.restaurant.service.domain.outbox.scheduler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.outbox.OutboxScheduler;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.restaurant.service.domain.outbox.model.OrderOutboxMessage;
import com.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.RestaurantApprovalResponseMessagePublisher;

//@Slf4j
@Component
public class OrderOutboxScheduler implements OutboxScheduler {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderOutboxScheduler.class);

    private final OrderOutboxHelper orderOutboxHelper;
    private final RestaurantApprovalResponseMessagePublisher responseMessagePublisher;

    public OrderOutboxScheduler(OrderOutboxHelper orderOutboxHelper,
                                RestaurantApprovalResponseMessagePublisher responseMessagePublisher) {
        this.orderOutboxHelper = orderOutboxHelper;
        this.responseMessagePublisher = responseMessagePublisher;
    }

    @Transactional
    @Scheduled(fixedRateString = "${restaurant-service.outbox-scheduler-fixed-rate}",
            initialDelayString = "${restaurant-service.outbox-scheduler-initial-delay}")
    @Override
    public void processOutboxMessage() {
        Optional<List<OrderOutboxMessage>> outboxMessagesResponse =
                orderOutboxHelper.getOrderOutboxMessageByOutboxStatus(OutboxStatus.STARTED);
        if (outboxMessagesResponse.isPresent() && outboxMessagesResponse.get().size() > 0) {
            List<OrderOutboxMessage> outboxMessages = outboxMessagesResponse.get();
            LOGGER.info("Received {} OrderOutboxMessage with ids {}, sending to message bus!", outboxMessages.size(),
                    outboxMessages.stream().map(outboxMessage ->
                            outboxMessage.getId().toString()).collect(Collectors.joining(",")));
            outboxMessages.forEach(orderOutboxMessage ->
                    responseMessagePublisher.publish(orderOutboxMessage,
                            orderOutboxHelper::updateOutboxStatus));
            LOGGER.info("{} OrderOutboxMessage sent to message bus!", outboxMessages.size());
        }
    }



}