package com.food.ordering.system.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaMessageHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageHelper.class);

	public <T> ListenableFutureCallback<SendResult<String, T>> getKafkaCallback(
			String responseTopicName, T avroModel, String orderId, String avroModelName) {

		return new ListenableFutureCallback<SendResult<String, T>>() {
			@Override
			public void onFailure(Throwable ex) {
				LOGGER.error("Error while sending Avromodel : '{}' " 
						+ "message '{}' to topic '{}'",
						avroModelName, avroModel.toString(), responseTopicName, ex);
			}

			@Override
			public void onSuccess(SendResult<String, T> result) {
				RecordMetadata metadata = result.getRecordMetadata();
				LOGGER.info(
						"Received successful response from Kafka for order id : {}"
								+ ", Topic : {}, Partition : {}, Offset : {}, Timestamp : {}",
								orderId, 
								metadata.topic(), 
								metadata.partition(), 
								metadata.offset(),
								metadata.timestamp());
			}
		};
	}
}
