package ru.netology.money_transfer_service.exception;

/**
 * Исключение при недостатке средств
 */
public class InsufficientFundsException extends AppException {
    public InsufficientFundsException() {
        super("Insufficient funds", ErrorCode.INSUFFICIENT_FUNDS);
    }
}