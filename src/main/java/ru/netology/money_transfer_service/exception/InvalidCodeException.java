package ru.netology.money_transfer_service.exception;

public class InvalidCodeException extends AppException {
    public InvalidCodeException() {
        super("Invalid confirmation code", 3); // Код ошибки 3 для неверного кода
    }
}
