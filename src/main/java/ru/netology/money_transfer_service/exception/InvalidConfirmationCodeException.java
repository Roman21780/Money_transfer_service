package ru.netology.money_transfer_service.exception;

// Исключение при неверном коде подтверждения
public class InvalidConfirmationCodeException extends AppException {
    public InvalidConfirmationCodeException() {
        super("Invalid confirmation code", 3); // ID 3 для кода
    }
}
