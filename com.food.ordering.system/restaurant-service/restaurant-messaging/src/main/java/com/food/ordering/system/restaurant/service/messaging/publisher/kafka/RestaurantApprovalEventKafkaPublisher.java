package com.food.ordering.system.restaurant.service.messaging.publisher.kafka;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class RestaurantApprovalEventKafkaPublisher {
//implements RestaurantApprovalResponseMessagePublisher {
//	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApprovalEventKafkaPublisher.class);
//
//    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
//    private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
//    private final RestaurantServiceConfigData restaurantServiceConfigData;
//    private final KafkaMessageHelper kafkaMessageHelper;
//
//    public RestaurantApprovalEventKafkaPublisher(RestaurantMessagingDataMapper dataMapper,
//                                                 KafkaProducer<String, RestaurantApprovalResponseAvroModel>
//                                                         kafkaProducer,
//                                                 RestaurantServiceConfigData restaurantServiceConfigData,
//                                                 KafkaMessageHelper kafkaMessageHelper) {
//        this.restaurantMessagingDataMapper = dataMapper;
//        this.kafkaProducer = kafkaProducer;
//        this.restaurantServiceConfigData = restaurantServiceConfigData;
//        this.kafkaMessageHelper = kafkaMessageHelper;
//    }
//
//
//    @Override
//    public void publish(OrderOutboxMessage orderOutboxMessage,
//                        BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback) {
//        OrderEventPayload orderEventPayload =
//                kafkaMessageHelper.getOrderEventPayload(orderOutboxMessage.getPayload(),
//                        OrderEventPayload.class);
//
//        String sagaId = orderOutboxMessage.getSagaId().toString();
//
//        LOGGER.info("Received OrderOutboxMessage for order id: {} and saga id: {}",
//                orderEventPayload.getOrderId(),
//                sagaId);
//        try {
//            RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel =
//                    restaurantMessagingDataMapper
//                            .orderEventPayloadToRestaurantApprovalResponseAvroModel(sagaId, orderEventPayload);
//
//            kafkaProducer.send(restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
//                    sagaId,
//                    restaurantApprovalResponseAvroModel,
//                    kafkaMessageHelper.getKafkaCallback(restaurantServiceConfigData
//                                    .getRestaurantApprovalResponseTopicName(),
//                            restaurantApprovalResponseAvroModel,
//                            orderOutboxMessage,
//                            outboxCallback,
//                            orderEventPayload.getOrderId(),
//                            "RestaurantApprovalResponseAvroModel"));
//
//            LOGGER.info("RestaurantApprovalResponseAvroModel sent to kafka for order id: {} and saga id: {}",
//                    restaurantApprovalResponseAvroModel.getOrderId(), sagaId);
//        } catch (Exception e) {
//            LOGGER.error("Error while sending RestaurantApprovalResponseAvroModel message" +
//                            " to kafka with order id: {} and saga id: {}, error: {}",
//                    orderEventPayload.getOrderId(), sagaId, e.getMessage());
//        }
//    }

}
