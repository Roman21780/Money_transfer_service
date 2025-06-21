package ru.netology.money_transfer_service.exception;

// Базовый класс для всех кастомных исключений
public class AppException extends RuntimeException {
    private final int id;

    public AppException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
