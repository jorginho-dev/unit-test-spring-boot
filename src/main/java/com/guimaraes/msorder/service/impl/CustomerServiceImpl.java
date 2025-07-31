package com.guimaraes.msorder.service.impl;

import com.guimaraes.msorder.controller.dto.CustomerDTO;
import com.guimaraes.msorder.controller.dto.CustomerRequestDTO;
import com.guimaraes.msorder.model.Customer;
import com.guimaraes.msorder.exception.BusinessException;
import com.guimaraes.msorder.mapper.CustomerMapper;
import com.guimaraes.msorder.repository.CustomerRepository;
import com.guimaraes.msorder.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return this.customerRepository.findAll().stream().map(CustomerMapper.INSTANCE::toCustomerDTO).toList();
    }

    @Override
    public CustomerDTO findById(Long id) {
        var customerEntity = this.customerRepository.findById(id).orElseThrow(() -> new BusinessException("Customer not found!"));
        return CustomerMapper.INSTANCE.toCustomerDTO(customerEntity);
    }

    @Override
    public CustomerDTO create(CustomerRequestDTO customerRequestDTO) {
        var customerEntity = this.customerRepository.save(new Customer(null, customerRequestDTO.name()));
        return CustomerMapper.INSTANCE.toCustomerDTO(customerEntity);
    }

    @Override
    public CustomerDTO update(Long id, CustomerRequestDTO customerRequestDTO) {
        var customerEntity = this.customerRepository.findById(id).orElseThrow(() -> new BusinessException("Customer not found!"));
        customerEntity.setName(customerRequestDTO.name());
        var customerEntityUpdated = this.customerRepository.save(customerEntity);
        return CustomerMapper.INSTANCE.toCustomerDTO(customerEntityUpdated);
    }

    @Override
    public void delete(Long id) {
        var customerEntity = this.customerRepository.findById(id).orElseThrow(() -> new BusinessException("Customer not found!"));
        this.customerRepository.delete(customerEntity);
    }
}
