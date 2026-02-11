package com.asyncflow.checkout.api.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank(message = "Código do produto é obrigatório")
        String productCode,

        @Min(value = 1, message = "Quantidade deve ser no mínimo 1")
        Integer quantity,

        @NotNull(message = "Preço é obrigatório")
        BigDecimal price
) {}
