package com.guimaraes.msorder.service;

import com.guimaraes.msorder.controller.dto.OrderRequestDTO;
import com.guimaraes.msorder.controller.dto.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO findById(Long id);
    OrderResponseDTO cancelById(Long id);
    OrderResponseDTO create(OrderRequestDTO orderRequestDTO);

}
