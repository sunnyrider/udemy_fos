package com.food.ordering.system.kafka.producer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.food.ordering.system.kafka.config.data.KafkaConfigData;
import com.food.ordering.system.kafka.config.data.KafkaProducerConfigData;

@Configuration
public class KafkaProducerConfig<K extends Serializable, V extends SpecificRecordBase> {

	private final KafkaConfigData kafkaConfigData;
	private final KafkaProducerConfigData kafkaProducerConfigData;

	public KafkaProducerConfig(KafkaConfigData kafkaConfigData, 
			KafkaProducerConfigData kafkaProducerConfigData) {
		this.kafkaConfigData = kafkaConfigData;
		this.kafkaProducerConfigData = kafkaProducerConfigData;
	}

	@Bean
	public Map<String, Object> producerConfig() {
		Map<String, Object> map = new HashMap<>();

		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigData.getBootstrapServers());
		map.put(kafkaConfigData.getSchemaRegistryUrlKey(), kafkaConfigData.getSchemaRegistryUrl());
		map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProducerConfigData.getKeySerializerClass());
		map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProducerConfigData.getValueSerializerClass());
		map.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProducerConfigData.getBatchSize() * 
				kafkaProducerConfigData.getBatchSizeBoostFactor());
		map.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProducerConfigData.getLingerMs());
		map.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, kafkaProducerConfigData.getCompressionType());
		map.put(ProducerConfig.ACKS_CONFIG, kafkaProducerConfigData.getAcks());
		map.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaProducerConfigData.getRequestTimeoutMs());
		map.put(ProducerConfig.RETRIES_CONFIG, kafkaProducerConfigData.getRetryCount());

		return map;
	}

	@Bean
	public ProducerFactory<K, V> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	@Bean
	public KafkaTemplate<K, V> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
