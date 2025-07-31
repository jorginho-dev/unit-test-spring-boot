package com.guimaraes.msorder.mapper;

import com.guimaraes.msorder.controller.dto.CustomerDTO;
import com.guimaraes.msorder.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toCustomerDTO(Customer customer);
}
