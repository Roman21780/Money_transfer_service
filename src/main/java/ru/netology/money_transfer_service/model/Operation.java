package ru.netology.money_transfer_service.model;

import java.math.BigDecimal;

/**
 * Модель операции перевода денег
 *
 * @param id Уникальный идентификатор операции
 * @param cardFrom Номер карты отправителя
 * @param cardTo Номер карты получателя
 * @param amount Сумма перевода
 * @param commission Комиссия
 * @param currency Валюта операции
 * @param status Статус операции
 * @param verificationCode Код подтверждения
 */
public record Operation(
        String id,
        String cardFrom,
        String cardTo,
        BigDecimal amount,
        BigDecimal commission,
        String currency,
        String status,
        String verificationCode
) {

    // Метод для изменения статуса (создает новую операцию с обновленным статусом)
    public Operation withStatus(String newStatus) {
        return new Operation(
                id, cardFrom, cardTo, amount,
                commission, currency, newStatus, verificationCode
        );
    }
}