package com.guimaraes.msorder.mapper;

import com.guimaraes.msorder.controller.dto.ProductDTO;
import com.guimaraes.msorder.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}
