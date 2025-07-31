package com.guimaraes.msorder.service.impl;

import com.guimaraes.msorder.controller.dto.CustomerRequestDTO;
import com.guimaraes.msorder.exception.BusinessException;
import com.guimaraes.msorder.model.Customer;
import com.guimaraes.msorder.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Test
    void shouldReturnCustomerWhenCreateCustomerSuccess() {
        var name = "Jorge Guimarães";
        var customer = new Customer(1L, name);
        Mockito.when(this.customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        var customerDto = customerService.create(new CustomerRequestDTO("Jorge Guimarães"));

        Assertions.assertEquals(1L, customerDto.id());
        Assertions.assertEquals(name, customerDto.name());
    }

    @Test
    void shouldReturnCustomerWhenCustomerExists() {
        var name = "Jorge Guimarães";
        var customer = new Customer(1L, name);
        Mockito.when(this.customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

        var customerDto = customerService.findById(1L);

        Assertions.assertEquals(1L, customerDto.id());
        Assertions.assertEquals(name, customerDto.name());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound(){
        Mockito.when(this.customerRepository.findById(Mockito.anyLong())).thenThrow(new BusinessException("Customer not found!"));

        Assertions.assertThrows(BusinessException.class, () -> customerService.findById(1L));
    }

}