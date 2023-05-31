package com.food.ordering.system.restaurant.service.domain.config;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Data
@Configuration
@ConfigurationProperties(prefix = "restaurant-service")
public class RestaurantServiceConfigData {
    private String restaurantApprovalRequestTopicName;
    private String restaurantApprovalResponseTopicName;

    public RestaurantServiceConfigData(String restaurantApprovalRequestTopicName,
			String restaurantApprovalResponseTopicName) {
		this.restaurantApprovalRequestTopicName = restaurantApprovalRequestTopicName;
		this.restaurantApprovalResponseTopicName = restaurantApprovalResponseTopicName;
	}

	public String getRestaurantApprovalRequestTopicName() {
		return restaurantApprovalRequestTopicName;
	}

	public void setRestaurantApprovalRequestTopicName(String restaurantApprovalRequestTopicName) {
		this.restaurantApprovalRequestTopicName = restaurantApprovalRequestTopicName;
	}

	public String getRestaurantApprovalResponseTopicName() {
		return restaurantApprovalResponseTopicName;
	}

	public void setRestaurantApprovalResponseTopicName(String restaurantApprovalResponseTopicName) {
		this.restaurantApprovalResponseTopicName = restaurantApprovalResponseTopicName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(restaurantApprovalRequestTopicName, restaurantApprovalResponseTopicName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantServiceConfigData other = (RestaurantServiceConfigData) obj;
		return Objects.equals(restaurantApprovalRequestTopicName, other.restaurantApprovalRequestTopicName)
				&& Objects.equals(restaurantApprovalResponseTopicName, other.restaurantApprovalResponseTopicName);
	}

	@Override
	public String toString() {
		return "RestaurantServiceConfigData [restaurantApprovalRequestTopicName=" + restaurantApprovalRequestTopicName
				+ ", restaurantApprovalResponseTopicName=" + restaurantApprovalResponseTopicName + "]";
	}

}
