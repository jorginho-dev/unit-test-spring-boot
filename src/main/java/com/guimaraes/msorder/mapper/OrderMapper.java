package com.guimaraes.msorder.mapper;

import com.guimaraes.msorder.controller.dto.OrderRequestDTO;
import com.guimaraes.msorder.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(OrderRequestDTO orderRequestDTO);
}
