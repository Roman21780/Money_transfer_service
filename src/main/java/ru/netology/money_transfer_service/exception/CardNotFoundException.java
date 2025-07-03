package ru.netology.money_transfer_service.exception;

/**
 * Исключение при отсутствии карты
 */
public class CardNotFoundException extends AppException {
    public CardNotFoundException() {
        super("Card not found", ErrorCode.CARD_NOT_FOUND);
    }
}