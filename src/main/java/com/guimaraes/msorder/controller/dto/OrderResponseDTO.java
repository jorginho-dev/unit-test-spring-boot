package com.guimaraes.msorder.controller.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class OrderResponseDTO {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private CustomerDTO customer;

    @Getter
    @Setter
    private List<ProductDTO> products;

    @Getter
    private BigDecimal amount;

    @Getter
    @Setter
    private int isCanceled;

    public OrderResponseDTO(Long id, CustomerDTO customer, List<ProductDTO> products, int isCanceled) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.isCanceled = isCanceled;

        amount = products.stream()
                .map(ProductDTO::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
