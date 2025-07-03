package ru.netology.money_transfer_service.model;

import java.math.BigDecimal;

public record Card(
        String number,
        String validTill,
        String cvv,
        BigDecimal balance
) {
    // Метод для создания копии с новым балансом
    public Card withBalance(BigDecimal newBalance) {
        return new Card(number, validTill, cvv, newBalance);
    }
}