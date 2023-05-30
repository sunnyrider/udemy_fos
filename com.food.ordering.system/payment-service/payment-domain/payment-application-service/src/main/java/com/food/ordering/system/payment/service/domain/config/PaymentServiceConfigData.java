package com.food.ordering.system.payment.service.domain.config;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payment-service")
public class PaymentServiceConfigData {

	private String paymentRequestTopicName;
	private String paymentResponseTopicName;

	public PaymentServiceConfigData(String paymentRequestTopicName, String paymentResponseTopicName) {
		super();
		this.paymentRequestTopicName = paymentRequestTopicName;
		this.paymentResponseTopicName = paymentResponseTopicName;
	}

	public String getPaymentRequestTopicName() {
		return paymentRequestTopicName;
	}
	public void setPaymentRequestTopicName(String paymentRequestTopicName) {
		this.paymentRequestTopicName = paymentRequestTopicName;
	}
	public String getPaymentResponseTopicName() {
		return paymentResponseTopicName;
	}
	public void setPaymentResponseTopicName(String paymentResponseTopicName) {
		this.paymentResponseTopicName = paymentResponseTopicName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentRequestTopicName, paymentResponseTopicName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentServiceConfigData other = (PaymentServiceConfigData) obj;
		return Objects.equals(paymentRequestTopicName, other.paymentRequestTopicName)
				&& Objects.equals(paymentResponseTopicName, other.paymentResponseTopicName);
	}

	@Override
	public String toString() {
		return "PaymentServiceConfigData [paymentRequestTopicName=" + paymentRequestTopicName
				+ ", paymentResponseTopicName=" + paymentResponseTopicName + "]";
	}
}
