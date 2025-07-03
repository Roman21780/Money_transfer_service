package ru.netology.money_transfer_service.dto;

/**
 * Запрос на подтверждение операции
 */
public record ConfirmRequest(
        String operationId,
        String code
) {
    // Для record геттеры создаются автоматически с именами operationId() и code()
    // без префикса get
}