package com.guimaraes.msorder.controller;

import com.guimaraes.msorder.controller.dto.OrderRequestDTO;
import com.guimaraes.msorder.controller.dto.OrderResponseDTO;
import com.guimaraes.msorder.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(this.orderService.create(orderRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> cancel(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.cancelById(id));
    }

}
