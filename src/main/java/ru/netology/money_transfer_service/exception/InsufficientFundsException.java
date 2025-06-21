package ru.netology.money_transfer_service.exception;

// Исключение при недостатке средств
public class InsufficientFundsException extends AppException {
    public InsufficientFundsException() {
        super("Insufficient funds", 2); // ID 2 для баланса
    }
}
