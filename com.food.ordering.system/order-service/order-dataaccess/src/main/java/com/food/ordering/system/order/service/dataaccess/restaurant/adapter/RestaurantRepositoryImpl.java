package com.food.ordering.system.order.service.dataaccess.restaurant.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.dataaccess.restaurant.repository.RestaurantJpaRepository;
import com.food.ordering.system.order.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

	private final RestaurantJpaRepository restaurantJpaRepository;
	private RestaurantDataAccessMapper restaurantDataAccessMapper;

	public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository,
			RestaurantDataAccessMapper restaurantDataAccessMapper) {
		this.restaurantJpaRepository = restaurantJpaRepository;
		this.restaurantDataAccessMapper = restaurantDataAccessMapper;
	}

	@Override
	public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
		List<UUID> restaurantProducts = restaurantDataAccessMapper
				.restaurantToRestaurantProduct(restaurant);

		Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(),
                        restaurantProducts);

		return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
	}
}