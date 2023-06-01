package com.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;

public interface OrderApprovedMessagePublisher extends  DomainEventPublisher<OrderApprovedEvent> {

//    void publish(OrderOutboxMessage orderOutboxMessage,
//                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
