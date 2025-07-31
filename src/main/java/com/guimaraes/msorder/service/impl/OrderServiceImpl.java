package com.guimaraes.msorder.service.impl;

import com.guimaraes.msorder.controller.dto.OrderRequestDTO;
import com.guimaraes.msorder.controller.dto.OrderResponseDTO;
import com.guimaraes.msorder.controller.dto.ProductDTO;
import com.guimaraes.msorder.exception.BusinessException;
import com.guimaraes.msorder.mapper.CustomerMapper;
import com.guimaraes.msorder.mapper.OrderMapper;
import com.guimaraes.msorder.mapper.ProductMapper;
import com.guimaraes.msorder.repository.CustomerRepository;
import com.guimaraes.msorder.repository.OrderRepository;
import com.guimaraes.msorder.repository.ProductRepository;
import com.guimaraes.msorder.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        var orderEntity = this.orderRepository.findById(id).orElseThrow(() -> new BusinessException("Order not found!"));
        var customerEntity = this.customerRepository.findById(orderEntity.getCustomerId()).orElseThrow(() -> new BusinessException("Customer not found!"));

        List<ProductDTO> productDTO = orderEntity.getProducts().stream().map(productId -> {
            var productEntity = this.productRepository.findById(productId);
            return ProductMapper.INTANCE.toProductDTO(productEntity.orElseThrow(() -> new BusinessException("Product not found!")));
        }).toList();

        var customerDTO = CustomerMapper.INSTANCE.toCustomerDTO(customerEntity);
        return new OrderResponseDTO(orderEntity.getId(), customerDTO, productDTO, orderEntity.getIsCanceled());
    }

    @Override
    public OrderResponseDTO cancelById(Long id) {
        var orderEntity = this.orderRepository.findById(id).orElseThrow(() -> new BusinessException("Order not found!"));
        orderEntity.setIsCanceled(1);
        var customerEntity = this.customerRepository.findById(orderEntity.getCustomerId()).orElseThrow(() -> new BusinessException("Customer not found!"));
        List<ProductDTO> productDTO = orderEntity.getProducts().stream().map(productId -> {
            var productEntity = this.productRepository.findById(productId);
            return ProductMapper.INTANCE.toProductDTO(productEntity.orElseThrow(() -> new BusinessException("Product not found!")));
        }).toList();
        this.orderRepository.save(orderEntity);
        var customerDTO = CustomerMapper.INSTANCE.toCustomerDTO(customerEntity);
        return new OrderResponseDTO(orderEntity.getId(), customerDTO, productDTO, orderEntity.getIsCanceled());
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO orderRequestDTO) {
        var customerEntity = this.customerRepository.findById(orderRequestDTO.customerId()).orElseThrow(() -> new BusinessException("Customer not found!"));
        var customerDTO = CustomerMapper.INSTANCE.toCustomerDTO(customerEntity);
        var orderEntity = OrderMapper.INSTANCE.toOrder(orderRequestDTO);
        var productDTO = this.productRepository.findAllById(orderEntity.getProducts()).stream().map(ProductMapper.INTANCE::toProductDTO).toList();
        var orderCreated = this.orderRepository.save(orderEntity);
        return new OrderResponseDTO(orderCreated.getId(), customerDTO, productDTO, orderCreated.getIsCanceled());
    }
}
