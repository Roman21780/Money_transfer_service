package ru.netology.money_transfer_service.exception;


// Исключение при невалидных данных карты
public class InvalidCardDataException extends AppException {
    public InvalidCardDataException(String field) {
        super("Invalid card data: " + field, 4); // ID 4 для данных
    }
}
