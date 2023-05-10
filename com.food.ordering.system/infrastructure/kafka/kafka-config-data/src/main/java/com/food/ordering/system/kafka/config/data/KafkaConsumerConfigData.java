package com.food.ordering.system.kafka.config.data;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {

    private String keyDeserializer;
    private String valueDeserializer;
    private String autoOffsetReset;
    private String specificAvroReaderKey;
    private String specificAvroReader;
    private Boolean batchListener;
    private Boolean autoStartup;
    private Integer concurrencyLevel;
    private Integer sessionTimeoutMs;
    private Integer heartbeatIntervalMs;
    private Integer maxPollIntervalMs;
    private Long pollTimeoutMs;
    private Integer maxPollRecords;
    private Integer maxPartitionFetchBytesDefault;
    private Integer maxPartitionFetchBytesBoostFactor;


    public KafkaConsumerConfigData() {
    	
    }

	public KafkaConsumerConfigData(String keyDeserializer, String valueDeserializer, String autoOffsetReset,
			String specificAvroReaderKey, String specificAvroReader, Boolean batchListener, Boolean autoStartup,
			Integer concurrencyLevel, Integer sessionTimeoutMs, Integer heartbeatIntervalMs, Integer maxPollIntervalMs,
			Long pollTimeoutMs, Integer maxPollRecords, Integer maxPartitionFetchBytesDefault,
			Integer maxPartitionFetchBytesBoostFactor) {
		super();
		this.keyDeserializer = keyDeserializer;
		this.valueDeserializer = valueDeserializer;
		this.autoOffsetReset = autoOffsetReset;
		this.specificAvroReaderKey = specificAvroReaderKey;
		this.specificAvroReader = specificAvroReader;
		this.batchListener = batchListener;
		this.autoStartup = autoStartup;
		this.concurrencyLevel = concurrencyLevel;
		this.sessionTimeoutMs = sessionTimeoutMs;
		this.heartbeatIntervalMs = heartbeatIntervalMs;
		this.maxPollIntervalMs = maxPollIntervalMs;
		this.pollTimeoutMs = pollTimeoutMs;
		this.maxPollRecords = maxPollRecords;
		this.maxPartitionFetchBytesDefault = maxPartitionFetchBytesDefault;
		this.maxPartitionFetchBytesBoostFactor = maxPartitionFetchBytesBoostFactor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KafkaConsumerConfigData other = (KafkaConsumerConfigData) obj;
		return Objects.equals(autoOffsetReset, other.autoOffsetReset) && Objects.equals(autoStartup, other.autoStartup)
				&& Objects.equals(batchListener, other.batchListener)
				&& Objects.equals(concurrencyLevel, other.concurrencyLevel)
				&& Objects.equals(heartbeatIntervalMs, other.heartbeatIntervalMs)
				&& Objects.equals(keyDeserializer, other.keyDeserializer)
				&& Objects.equals(maxPartitionFetchBytesBoostFactor, other.maxPartitionFetchBytesBoostFactor)
				&& Objects.equals(maxPartitionFetchBytesDefault, other.maxPartitionFetchBytesDefault)
				&& Objects.equals(maxPollIntervalMs, other.maxPollIntervalMs)
				&& Objects.equals(maxPollRecords, other.maxPollRecords)
				&& Objects.equals(pollTimeoutMs, other.pollTimeoutMs)
				&& Objects.equals(sessionTimeoutMs, other.sessionTimeoutMs)
				&& Objects.equals(specificAvroReader, other.specificAvroReader)
				&& Objects.equals(specificAvroReaderKey, other.specificAvroReaderKey)
				&& Objects.equals(valueDeserializer, other.valueDeserializer);
	}

	public String getAutoOffsetReset() {
		return autoOffsetReset;
	}

    public Boolean getAutoStartup() {
		return autoStartup;
	}

    public Boolean getBatchListener() {
		return batchListener;
	}
	public Integer getConcurrencyLevel() {
		return concurrencyLevel;
	}
	public Integer getHeartbeatIntervalMs() {
		return heartbeatIntervalMs;
	}
	public String getKeyDeserializer() {
		return keyDeserializer;
	}
	public Integer getMaxPartitionFetchBytesBoostFactor() {
		return maxPartitionFetchBytesBoostFactor;
	}
	public Integer getMaxPartitionFetchBytesDefault() {
		return maxPartitionFetchBytesDefault;
	}
	public Integer getMaxPollIntervalMs() {
		return maxPollIntervalMs;
	}
	public Integer getMaxPollRecords() {
		return maxPollRecords;
	}
	public Long getPollTimeoutMs() {
		return pollTimeoutMs;
	}
	public Integer getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}
	public String getSpecificAvroReader() {
		return specificAvroReader;
	}
	public String getSpecificAvroReaderKey() {
		return specificAvroReaderKey;
	}
	public String getValueDeserializer() {
		return valueDeserializer;
	}
	@Override
	public int hashCode() {
		return Objects.hash(autoOffsetReset, autoStartup, batchListener, concurrencyLevel, heartbeatIntervalMs,
				keyDeserializer, maxPartitionFetchBytesBoostFactor, maxPartitionFetchBytesDefault, maxPollIntervalMs,
				maxPollRecords, pollTimeoutMs, sessionTimeoutMs, specificAvroReader, specificAvroReaderKey,
				valueDeserializer);
	}
	public void setAutoOffsetReset(String autoOffsetReset) {
		this.autoOffsetReset = autoOffsetReset;
	}
	public void setAutoStartup(Boolean autoStartup) {
		this.autoStartup = autoStartup;
	}
	public void setBatchListener(Boolean batchListener) {
		this.batchListener = batchListener;
	}
	public void setConcurrencyLevel(Integer concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}
	public void setHeartbeatIntervalMs(Integer heartbeatIntervalMs) {
		this.heartbeatIntervalMs = heartbeatIntervalMs;
	}
	public void setKeyDeserializer(String keyDeserializer) {
		this.keyDeserializer = keyDeserializer;
	}
	public void setMaxPartitionFetchBytesBoostFactor(Integer maxPartitionFetchBytesBoostFactor) {
		this.maxPartitionFetchBytesBoostFactor = maxPartitionFetchBytesBoostFactor;
	}
	public void setMaxPartitionFetchBytesDefault(Integer maxPartitionFetchBytesDefault) {
		this.maxPartitionFetchBytesDefault = maxPartitionFetchBytesDefault;
	}
	public void setMaxPollIntervalMs(Integer maxPollIntervalMs) {
		this.maxPollIntervalMs = maxPollIntervalMs;
	}
	public void setMaxPollRecords(Integer maxPollRecords) {
		this.maxPollRecords = maxPollRecords;
	}
	public void setPollTimeoutMs(Long pollTimeoutMs) {
		this.pollTimeoutMs = pollTimeoutMs;
	}
	public void setSessionTimeoutMs(Integer sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}
	public void setSpecificAvroReader(String specificAvroReader) {
		this.specificAvroReader = specificAvroReader;
	}
	public void setSpecificAvroReaderKey(String specificAvroReaderKey) {
		this.specificAvroReaderKey = specificAvroReaderKey;
	}
	public void setValueDeserializer(String valueDeserializer) {
		this.valueDeserializer = valueDeserializer;
	}
	@Override
	public String toString() {
		return "KafkaConsumerConfigData [keyDeserializer=" + keyDeserializer + ", valueDeserializer="
				+ valueDeserializer + ", autoOffsetReset=" + autoOffsetReset + ", specificAvroReaderKey="
				+ specificAvroReaderKey + ", specificAvroReader=" + specificAvroReader + ", batchListener="
				+ batchListener + ", autoStartup=" + autoStartup + ", concurrencyLevel=" + concurrencyLevel
				+ ", sessionTimeoutMs=" + sessionTimeoutMs + ", heartbeatIntervalMs=" + heartbeatIntervalMs
				+ ", maxPollIntervalMs=" + maxPollIntervalMs + ", pollTimeoutMs=" + pollTimeoutMs + ", maxPollRecords="
				+ maxPollRecords + ", maxPartitionFetchBytesDefault=" + maxPartitionFetchBytesDefault
				+ ", maxPartitionFetchBytesBoostFactor=" + maxPartitionFetchBytesBoostFactor + "]";
	}

}
