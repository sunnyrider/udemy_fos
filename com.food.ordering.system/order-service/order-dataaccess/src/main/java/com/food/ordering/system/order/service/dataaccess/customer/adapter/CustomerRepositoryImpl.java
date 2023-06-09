package com.food.ordering.system.order.service.dataaccess.customer.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;
import com.food.ordering.system.order.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.customer.repository.CustomerJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

	public final CustomerJpaRepository customerJpaRepository;
	public final CustomerDataAccessMapper customerDataAccessMapper;

	public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
			CustomerDataAccessMapper customerDataAccessMapper) {
		this.customerJpaRepository = customerJpaRepository;
		this.customerDataAccessMapper = customerDataAccessMapper;
	}

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
    	Optional<CustomerEntity> result = customerJpaRepository.findById(customerId);
        return result.map(customerDataAccessMapper::customerEntityToCustomer);
    }

}
