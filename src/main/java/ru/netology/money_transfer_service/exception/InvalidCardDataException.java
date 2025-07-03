package ru.netology.money_transfer_service.exception;

/**
 * Исключение при невалидных данных карты
 */
public class InvalidCardDataException extends AppException {
    public InvalidCardDataException(String field) {
        super("Invalid card data: " + field, ErrorCode.INVALID_CARD_DATA);
    }
}