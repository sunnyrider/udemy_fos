package com.food.ordering.system.kafka.producer;

import java.util.function.BiConsumer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.outbox.OutboxStatus;

@Component
public class KafkaMessageHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageHelper.class);

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T getOrderEventPayload(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not read {} object!", outputType.getName(), e);
            throw new OrderDomainException("Could not read " + outputType.getName() + " object!", e);
        }
    }

    public <T, U> ListenableFutureCallback<SendResult<String, T>>
    getKafkaCallback(String responseTopicName, T avroModel, U outboxMessage,
                     BiConsumer<U, OutboxStatus> outboxCallback,
                     String orderId, String avroModelName) {
        return new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Error while sending {} with message: {} and outbox type: {} to topic {}",
                        avroModelName, avroModel.toString(), outboxMessage.getClass().getName(), responseTopicName, ex);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                LOGGER.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            }
        };
    }
}
