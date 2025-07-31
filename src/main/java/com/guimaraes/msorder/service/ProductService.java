package com.guimaraes.msorder.service;

import com.guimaraes.msorder.controller.dto.ProductDTO;
import com.guimaraes.msorder.controller.dto.ProductRequestDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductRequestDTO productRequestDTO);
    List<ProductDTO> findAll();
}
