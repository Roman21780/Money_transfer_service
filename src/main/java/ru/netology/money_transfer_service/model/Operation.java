package ru.netology.money_transfer_service.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Operation {
    private String Id;     // UUID операции
    private String cardFrom;        // Номер карты отправителя
    private String cardTo;          // Номер карты получателя
    private BigDecimal amount;      // Сумма перевода
    private BigDecimal commission;  // Комиссия (0 по умолчанию)
    private String currency;        // Валюта
    private String status;          // Статус (CREATED/SUCCESS/FAILED)
    private String verificationCode; // Код подтверждения

    // Конструкторы
    public Operation() {}

    public Operation(String Id, String cardFrom, String cardTo,
                     BigDecimal amount, BigDecimal commission, String currency,
                     String status, String verificationCode) {
        this.Id = Id;
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.amount = amount;
        this.commission = commission;
        this.currency = currency;
        this.status = status;
        this.verificationCode = verificationCode;
    }

    // Геттеры и сеттеры
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(String cardFrom) {
        this.cardFrom = cardFrom;
    }

    public String getCardTo() {
        return cardTo;
    }

    public void setCardTo(String cardTo) {
        this.cardTo = cardTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(Id, operation.Id) &&
                Objects.equals(cardFrom, operation.cardFrom) &&
                Objects.equals(cardTo, operation.cardTo) &&
                Objects.equals(amount, operation.amount) &&
                Objects.equals(commission, operation.commission) &&
                Objects.equals(currency, operation.currency) &&
                Objects.equals(status, operation.status) &&
                Objects.equals(verificationCode, operation.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, cardFrom, cardTo, amount, commission, currency, status, verificationCode);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId='" + Id + '\'' +
                ", cardFrom='" + cardFrom + '\'' +
                ", cardTo='" + cardTo + '\'' +
                ", amount=" + amount +
                ", commission=" + commission +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
