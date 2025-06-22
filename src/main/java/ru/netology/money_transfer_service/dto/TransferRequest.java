package ru.netology.money_transfer_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Objects;

@Data
public class TransferRequest {
    @NotBlank(message = "Card number is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    private String cardFromNumber;

    @NotBlank(message = "Card expiry date is required")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Expiry date must be in MM/YY format")
    private String cardFromValidTill;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cardFromCVV;

    @NotBlank(message = "Recipient card number is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    private String cardToNumber;

    @Valid
    @NotNull(message = "Amount is required")
    private Amount amount;

    @Data
    public static class Amount {
        @Min(value = 1, message = "Amount must be positive")
        private int value;

        @NotBlank(message = "Currency is required")
        @Pattern(regexp = "[A-Z]{3}", message = "Currency must be 3 uppercase letters")
        private String currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return Objects.equals(cardFromNumber, that.cardFromNumber) &&
                Objects.equals(cardFromValidTill, that.cardFromValidTill) &&
                Objects.equals(cardFromCVV, that.cardFromCVV) &&
                Objects.equals(cardToNumber, that.cardToNumber) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, amount);
    }
}
