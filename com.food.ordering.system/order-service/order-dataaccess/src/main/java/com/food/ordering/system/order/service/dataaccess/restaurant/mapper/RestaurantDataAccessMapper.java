package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;

@Component
public class RestaurantDataAccessMapper {

	public List<UUID> restaurantToRestaurantProduct(Restaurant restaurant) {
		return restaurant.getProducts().stream()
				.map(item -> item.getId().getValue())
				.toList();
	}

	/**
	 * Return a Restaurant instance if one of the restaurants 
	 * in the parameter list matches with the database
	 * 
	 * @param restaurantEntities
	 * @return
	 */
	public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {

		RestaurantEntity restaurantEntity = restaurantEntities.stream()
				.findFirst().orElseThrow(() ->
		new RestaurantDataAccessException("Restaurant could not be found"));

		List<Product> products = restaurantEntities.stream()
				.map(item -> Product.builder()
						.productId(new ProductId(item.getProductId()))
						.Name(item.getProductName())
						.build()).toList();

		return Restaurant.builder()
				.withRestaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
				.withProducts(products)
				.withActive(restaurantEntity.getRestaurantActive())
				.build();
	}
}
