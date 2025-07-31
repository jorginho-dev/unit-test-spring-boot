package com.guimaraes.msorder.service.impl;

import com.guimaraes.msorder.controller.dto.ProductDTO;
import com.guimaraes.msorder.controller.dto.ProductRequestDTO;
import com.guimaraes.msorder.mapper.ProductMapper;
import com.guimaraes.msorder.model.Product;
import com.guimaraes.msorder.repository.ProductRepository;
import com.guimaraes.msorder.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO create(ProductRequestDTO productRequestDTO) {
        var productEntity = this.productRepository.save(new Product(null, productRequestDTO.name(), productRequestDTO.price()));
        return ProductMapper.INTANCE.toProductDTO(productEntity);
    }

    @Override
    public List<ProductDTO> findAll() {
        return  this.productRepository.findAll().stream().map(ProductMapper.INTANCE::toProductDTO).toList();
    }
}
