package ru.netology.money_transfer_service.dto;


import java.time.Instant;

public record ErrorResponse(
        String message,
        int id,
        String errorCode,
        long timestamp
) {
    // Компактный конструктор для валидации
    public ErrorResponse {
        if (id < 0) throw new IllegalArgumentException("ID must be positive");
        if (timestamp == 0) timestamp = Instant.now().toEpochMilli();
    }
}
