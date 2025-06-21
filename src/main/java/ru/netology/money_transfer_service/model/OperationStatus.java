package ru.netology.money_transfer_service.model;

public enum OperationStatus {
    CREATED,    // Операция создана
    SUCCESS,    // Успешно завершена
    FAILED;      // Ошибка выполнения

    @Override
    public String toString() {
        return name();
    }
}
