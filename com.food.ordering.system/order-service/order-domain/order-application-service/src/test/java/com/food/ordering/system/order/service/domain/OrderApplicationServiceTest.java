package com.food.ordering.system.order.service.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.create.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {

	@Autowired
	private OrderApplicationService orderApplicationService;

	@Autowired
	private OrderDataMapper orderDataMapper;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	private CreateOrderCommand createOrderCommand;
	private CreateOrderCommand createOrderCommandWrongPrice;
	private CreateOrderCommand createOrderCommandWrongProductPrice;
    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID RESTAURANT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID ORDER_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");
    private final UUID SAGA_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afa");
    private final BigDecimal PRICE = new BigDecimal("200.00");

//    @BeforeAll
    public void init() {
    	createOrderCommand = CreateOrderCommand.builder()
    			.withCustomerId(CUSTOMER_ID)
    			.withRestaurantId(RESTAURANT_ID)
    			.withAddress(OrderAddress.builder()
    					.withStreet("Augustastr. 64")
    					.withPostalCode("42119")
    					.withCity("Wuppertal")
    					.build())
    			.withPrice(PRICE)
    			.withItems(List.of(OrderItem.builder()
    					.withProductId(PRODUCT_ID)
    					.withQuantity(1)
    					.withPrice(new BigDecimal("50.00"))
    					.withSubTotal(new BigDecimal("50.00"))
    					.build(),
    					OrderItem.builder()
    					.withProductId(PRODUCT_ID)
    					.withQuantity(3)
    					.withPrice(new BigDecimal("50.00"))
    					.withSubTotal(new BigDecimal("150.00"))
    					.build()))
    			.build();

    	createOrderCommandWrongPrice = CreateOrderCommand.builder()
    			.withCustomerId(CUSTOMER_ID)
    			.withRestaurantId(RESTAURANT_ID)
    			.withAddress(OrderAddress.builder()
    					.withStreet("Fuhlrottstr. 201")
    					.withPostalCode("42119")
    					.withCity("Wuppertal")
    					.build())
    			.withPrice(new BigDecimal("250.00"))
    			.withItems(List.of(OrderItem.builder()
    					.withProductId(PRODUCT_ID)
    					.withQuantity(1)
    					.withPrice(new BigDecimal("50.00"))
    					.withSubTotal(new BigDecimal("50.00"))
    					.build(),
    					OrderItem.builder()
    					.withProductId(PRODUCT_ID)
    					.withQuantity(3)
    					.withPrice(new BigDecimal("50.00"))
    					.withSubTotal(new BigDecimal("150.00"))
    					.build()))
    			.build();

    	createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
    			.withCustomerId(CUSTOMER_ID)
    			.withRestaurantId(RESTAURANT_ID)
    			.withAddress(OrderAddress.builder()
    					.withStreet("Max-Horkheimer-Str. 14")
    					.withPostalCode("42119")
    					.withCity("Wuppertal")
    					.build())
    			.withPrice(new BigDecimal("210.00"))
    			.withItems(List.of(OrderItem.builder()
    					.withProductId(PRODUCT_ID)
    					.withQuantity(1)
    					.withPrice(new BigDecimal("60.00"))
    					.withSubTotal(new BigDecimal("60.00"))
    					.build(),
    					OrderItem.builder()
    					.withProductId(PRODUCT_ID)
    					.withQuantity(3)
    					.withPrice(new BigDecimal("50.00"))
    					.withSubTotal(new BigDecimal("150.00"))
    					.build()))
    			.build();

    	Customer customer = new Customer();
    	customer.setId(new CustomerId(CUSTOMER_ID));

    	Restaurant restaurantResponse = Restaurant.builder()
    			.withRestaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
    			.withProducts(List.of(new Product(new ProductId(PRODUCT_ID),
    					"Natursekt aus Alina",
    					new Money(new BigDecimal("50.00")))))
    			.withActive(true)
    			.build();

    	Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    	order.setId(new OrderId(ORDER_ID));

    	when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
    	when(restaurantRepository.findRestaurantInformation(
    			orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
    	.thenReturn(Optional.of(restaurantResponse));
    	when(orderRepository.save(any(Order.class))).thenReturn(order);
    }

//    @Test
    public void testCreateOrder() {
    	CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);

    	assertEquals(OrderStatus.PENDING, createOrderResponse.getOrderStatus());
    	assertEquals("Order created successfully", createOrderResponse.getMessage());
    	assertNotNull(createOrderResponse.getOrderTrackingId());
    }

//    @Test
    public void testCreateOrderWithWrongTotalPrice() {
    	OrderDomainException ex = assertThrows(OrderDomainException.class, 
    			() -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
    	assertEquals("Total price : 250.00 is not equal to the "
    			+ "order items total : 200.00!", ex.getMessage());
    }

//    @Test
    public void testCreateOrderWithWrongProductPrice() {
    	OrderDomainException ex = assertThrows(OrderDomainException.class, 
    			() -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
    	assertEquals("Order item price: 60.00" +
                " is not valid for product " + PRODUCT_ID, ex.getMessage());
    }

//    @Test
    public void testCreateOrderWithPassiveRestaurant() {
    	Restaurant restaurantResponse = Restaurant.builder()
    			.withRestaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
    			.withProducts(List.of(new Product(new ProductId(PRODUCT_ID),
    					"Natursekt aus Alina",
    					new Money(new BigDecimal("50.00")))))
    			.withActive(false)
    			.build();

    	when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
    		.thenReturn(Optional.of(restaurantResponse));
    	OrderDomainException ex = assertThrows(OrderDomainException.class,
    			() -> orderApplicationService.createOrder(createOrderCommand));

    	assertEquals("Restaurant with id : " + RESTAURANT_ID + " is currently not active!", ex.getMessage());
    }
}
