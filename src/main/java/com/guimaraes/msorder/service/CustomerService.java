package com.guimaraes.msorder.service;

import com.guimaraes.msorder.controller.dto.CustomerDTO;
import com.guimaraes.msorder.controller.dto.CustomerRequestDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();
    CustomerDTO findById(Long id);
    CustomerDTO create(CustomerRequestDTO customerRequestDTO);
    CustomerDTO update(Long id, CustomerRequestDTO customerRequestDTO);
    void delete(Long id);
}
