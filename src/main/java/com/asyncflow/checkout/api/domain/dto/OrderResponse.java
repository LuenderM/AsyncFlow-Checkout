package com.asyncflow.checkout.api.domain.dto;

import java.util.UUID;

public record OrderResponse(
        UUID id,
        String status,
        String message
) {}
