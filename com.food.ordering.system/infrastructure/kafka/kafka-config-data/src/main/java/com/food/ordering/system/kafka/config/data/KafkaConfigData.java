package com.food.ordering.system.kafka.config.data;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {

	private String bootstrapServers;
	private String schemaRegistryUrlKey;
	private String schemaRegistryUrl;
	private Integer numOfPartitions;
	private Short replicationFactor;

	public KafkaConfigData() {
	}

	public KafkaConfigData(String bootstrapServers, String schemaRegistryUrlKey, String schemaRegistryUrl,
			Integer numOfPartitions, Short replicationFactor) {
		super();
		this.bootstrapServers = bootstrapServers;
		this.schemaRegistryUrlKey = schemaRegistryUrlKey;
		this.schemaRegistryUrl = schemaRegistryUrl;
		this.numOfPartitions = numOfPartitions;
		this.replicationFactor = replicationFactor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KafkaConfigData other = (KafkaConfigData) obj;
		return Objects.equals(bootstrapServers, other.bootstrapServers)
				&& Objects.equals(numOfPartitions, other.numOfPartitions)
				&& Objects.equals(replicationFactor, other.replicationFactor)
				&& Objects.equals(schemaRegistryUrl, other.schemaRegistryUrl)
				&& Objects.equals(schemaRegistryUrlKey, other.schemaRegistryUrlKey);
	}

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public Integer getNumOfPartitions() {
		return numOfPartitions;
	}

	public Short getReplicationFactor() {
		return replicationFactor;
	}

	public String getSchemaRegistryUrl() {
		return schemaRegistryUrl;
	}

	public String getSchemaRegistryUrlKey() {
		return schemaRegistryUrlKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bootstrapServers, numOfPartitions, replicationFactor, schemaRegistryUrl,
				schemaRegistryUrlKey);
	}

	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public void setNumOfPartitions(Integer numOfPartitions) {
		this.numOfPartitions = numOfPartitions;
	}

	public void setReplicationFactor(Short replicationFactor) {
		this.replicationFactor = replicationFactor;
	}

	public void setSchemaRegistryUrl(String schemaRegistryUrl) {
		this.schemaRegistryUrl = schemaRegistryUrl;
	}

	public void setSchemaRegistryUrlKey(String schemaRegistryUrlKey) {
		this.schemaRegistryUrlKey = schemaRegistryUrlKey;
	}

	@Override
	public String toString() {
		return "KafkaConfigData [bootstrapServers=" + bootstrapServers + ", schemaRegistryUrlKey="
				+ schemaRegistryUrlKey + ", schemaRegistryUrl=" + schemaRegistryUrl + ", numOfPartitions="
				+ numOfPartitions + ", replicationFactor=" + replicationFactor + "]";
	}

}
