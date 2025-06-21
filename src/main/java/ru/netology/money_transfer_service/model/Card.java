package ru.netology.money_transfer_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Objects;

public class Card {
    private String number;
    private String validTill;
    private String cvv;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;

    private String currency;

    // Конструкторы
    public Card() {}

    public Card(String number, String validTill, String cvv, BigDecimal balance, String currency) {
        this.number = number;
        this.validTill = validTill;
        this.cvv = cvv;
        this.balance = balance;
        this.currency = currency;
    }

    // Геттеры и сеттеры
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        Card card = (Card) o;
        return Objects.equals(number, card.number) &&
                Objects.equals(validTill, card.validTill) &&
                Objects.equals(cvv, card.cvv) &&
                Objects.equals(balance, card.balance) &&
                Objects.equals(currency, card.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, validTill, cvv, balance, currency);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", validTill='" + validTill + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
