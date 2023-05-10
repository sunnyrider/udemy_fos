package com.food.ordering.system.kafka.config.data;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {

    private String keySerializerClass;
    private String valueSerializerClass;
    private String compressionType;
    private String acks;
    private Integer batchSize;
    private Integer batchSizeBoostFactor;
    private Integer lingerMs;
    private Integer requestTimeoutMs;
    private Integer retryCount;

    public KafkaProducerConfigData() {
    	
    }

    public KafkaProducerConfigData(String keySerializerClass, String valueSerializerClass, String compressionType,
			String acks, Integer batchSize, Integer batchSizeBoostFactor, Integer lingerMs, Integer requestTimeoutMs,
			Integer retryCount) {
		super();
		this.keySerializerClass = keySerializerClass;
		this.valueSerializerClass = valueSerializerClass;
		this.compressionType = compressionType;
		this.acks = acks;
		this.batchSize = batchSize;
		this.batchSizeBoostFactor = batchSizeBoostFactor;
		this.lingerMs = lingerMs;
		this.requestTimeoutMs = requestTimeoutMs;
		this.retryCount = retryCount;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KafkaProducerConfigData other = (KafkaProducerConfigData) obj;
		return Objects.equals(acks, other.acks) && Objects.equals(batchSize, other.batchSize)
				&& Objects.equals(batchSizeBoostFactor, other.batchSizeBoostFactor)
				&& Objects.equals(compressionType, other.compressionType)
				&& Objects.equals(keySerializerClass, other.keySerializerClass)
				&& Objects.equals(lingerMs, other.lingerMs) && Objects.equals(requestTimeoutMs, other.requestTimeoutMs)
				&& Objects.equals(retryCount, other.retryCount)
				&& Objects.equals(valueSerializerClass, other.valueSerializerClass);
	}

	public String getAcks() {
		return acks;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public Integer getBatchSizeBoostFactor() {
		return batchSizeBoostFactor;
	}
	public String getCompressionType() {
		return compressionType;
	}
	public String getKeySerializerClass() {
		return keySerializerClass;
	}
	public Integer getLingerMs() {
		return lingerMs;
	}
	public Integer getRequestTimeoutMs() {
		return requestTimeoutMs;
	}
	public Integer getRetryCount() {
		return retryCount;
	}
	public String getValueSerializerClass() {
		return valueSerializerClass;
	}
	@Override
	public int hashCode() {
		return Objects.hash(acks, batchSize, batchSizeBoostFactor, compressionType, keySerializerClass, lingerMs,
				requestTimeoutMs, retryCount, valueSerializerClass);
	}
	public void setAcks(String acks) {
		this.acks = acks;
	}
	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	public void setBatchSizeBoostFactor(Integer batchSizeBoostFactor) {
		this.batchSizeBoostFactor = batchSizeBoostFactor;
	}
	public void setCompressionType(String compressionType) {
		this.compressionType = compressionType;
	}
	public void setKeySerializerClass(String keySerializerClass) {
		this.keySerializerClass = keySerializerClass;
	}
	public void setLingerMs(Integer lingerMs) {
		this.lingerMs = lingerMs;
	}
	public void setRequestTimeoutMs(Integer requestTimeoutMs) {
		this.requestTimeoutMs = requestTimeoutMs;
	}
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	public void setValueSerializerClass(String valueSerializerClass) {
		this.valueSerializerClass = valueSerializerClass;
	}
	@Override
	public String toString() {
		return "KafkaProducerConfigData [keySerializerClass=" + keySerializerClass + ", valueSerializerClass="
				+ valueSerializerClass + ", compressionType=" + compressionType + ", acks=" + acks + ", batchSize="
				+ batchSize + ", batchSizeBoostFactor=" + batchSizeBoostFactor + ", lingerMs=" + lingerMs
				+ ", requestTimeoutMs=" + requestTimeoutMs + ", retryCount=" + retryCount + "]";
	}
}
