package ru.netology.money_transfer_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public record TransferRequest(
        @NotBlank @Size(min = 16, max = 16) String cardFromNumber,
        @NotBlank @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}") String cardFromValidTill,
        @NotBlank @Pattern(regexp = "\\d{3}") String cardFromCVV,
        @NotBlank @Size(min = 16, max = 16) String cardToNumber,
        @Valid @NotNull Amount amount
) {}
