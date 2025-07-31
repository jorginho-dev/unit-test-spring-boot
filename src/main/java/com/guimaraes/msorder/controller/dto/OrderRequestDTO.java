package com.guimaraes.msorder.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDTO(@NotNull Long customerId, @NotNull List<Long> products) {
}
