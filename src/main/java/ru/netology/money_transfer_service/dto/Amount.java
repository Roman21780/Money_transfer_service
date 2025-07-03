package ru.netology.money_transfer_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Amount(
        @Min(value = 1, message = "Amount must be positive")
        int value,

        @NotBlank(message = "Currency is required")
        @Pattern(regexp = "[A-Z]{3}", message = "Currency must be 3 uppercase letters")
        String currency
) {}

