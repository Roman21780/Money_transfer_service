package ru.netology.money_transfer_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

public class TransferRequest {
    @JsonProperty("cardFromNumber")
    private String cardFromNumber;

    @JsonProperty("cardFromValidTill")
    private String cardFromValidTill;

    @JsonProperty("cardFromCVV")
    private String cardFromCVV;

    @JsonProperty("cardToNumber")
    private String cardToNumber;

    @JsonProperty("amount")
    private Amount amount;

    // Геттеры и сеттеры
    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    // Вложенный класс Amount
    public static class Amount {
        @JsonProperty("value")
        private int value;

        @JsonProperty("currency")
        private String currency;

        // Геттеры и сеттеры
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Amount amount = (Amount) o;
            return value == amount.value && Objects.equals(currency, amount.currency);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, currency);
        }
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
