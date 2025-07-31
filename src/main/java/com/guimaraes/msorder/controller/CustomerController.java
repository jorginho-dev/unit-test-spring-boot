package com.guimaraes.msorder.controller;

import com.guimaraes.msorder.controller.dto.CustomerDTO;
import com.guimaraes.msorder.controller.dto.CustomerRequestDTO;
import com.guimaraes.msorder.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    ResponseEntity<List<CustomerDTO>> findAll() {
        return ResponseEntity.ok(this.customerService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> find(@PathVariable(name = "id") Long id) {
        var customer = this.customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        return ResponseEntity.ok(this.customerService.create(customerRequestDTO));
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerDTO> delete(@Valid @PathVariable Long id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        return ResponseEntity.ok(this.customerService.update(id, customerRequestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        this.customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
