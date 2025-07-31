package com.guimaraes.msorder.controller.dto;

import java.math.BigDecimal;

public record ProductDTO(Long id, String name, BigDecimal price) {
}
