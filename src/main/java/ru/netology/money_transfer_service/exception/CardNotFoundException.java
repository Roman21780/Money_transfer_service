package ru.netology.money_transfer_service.exception;

// Исключение при отсутствии карты
public class CardNotFoundException extends AppException {
    public CardNotFoundException() {
        super("Card not found", 1); // ID 1 для карты
    }
}
