package com.guimaraes.msorder.service.impl;

import com.guimaraes.msorder.controller.dto.CustomerDTO;
import com.guimaraes.msorder.controller.dto.OrderRequestDTO;
import com.guimaraes.msorder.controller.dto.OrderResponseDTO;
import com.guimaraes.msorder.controller.dto.ProductDTO;
import com.guimaraes.msorder.mapper.ProductMapper;
import com.guimaraes.msorder.model.Customer;
import com.guimaraes.msorder.model.Order;
import com.guimaraes.msorder.repository.CustomerRepository;
import com.guimaraes.msorder.repository.OrderRepository;
import com.guimaraes.msorder.repository.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    static Stream<Arguments> providerOderForAmount() {
        var customer = new CustomerDTO(1L, "Jorge");
        var products = List.of(new ProductDTO(1L, "Sapatilha", BigDecimal.valueOf(10)), new ProductDTO(2L, "Salto", BigDecimal.valueOf(20)));
        return Stream.of(
                Arguments.of(new OrderResponseDTO(1L, customer, products, 0), BigDecimal.valueOf(30)));
    }

    @ParameterizedTest
    @MethodSource("providerOderForAmount")
    void shouldReturnOrderWhenCreateOrderSuccess(OrderResponseDTO order, BigDecimal amount) {
        var orderRequest = new OrderRequestDTO(order.getCustomer().id(), order.getProducts().stream().map(ProductDTO::id).toList());
        var customerEntity = new Customer(order.getCustomer().id(), order.getCustomer().name());
        var products = order.getProducts().stream().map(ProductDTO::id).toList();
        var orderEntity = new Order(order.getId(), order.getCustomer().id(), products, 0);
        var productsEntity = order.getProducts().stream().map(ProductMapper.INTANCE::toProduct).toList();

        Mockito.when(this.customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customerEntity));
        Mockito.when(this.orderRepository.save(Mockito.any(Order.class))).thenReturn(orderEntity);
        Mockito.when(this.productRepository.findAllById(Mockito.anyList())).thenReturn(productsEntity);

        var result = orderService.create(orderRequest);

        assertEquals(amount, result.getAmount());
    }
}