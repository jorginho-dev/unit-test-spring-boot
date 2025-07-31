package com.guimaraes.msorder.controller;

import com.guimaraes.msorder.controller.dto.ProductDTO;
import com.guimaraes.msorder.controller.dto.ProductRequestDTO;
import com.guimaraes.msorder.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(this.productService.create(productRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(this.productService.findAll());
    }
}
