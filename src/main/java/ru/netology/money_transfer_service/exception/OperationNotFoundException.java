package ru.netology.money_transfer_service.exception;

public class OperationNotFoundException extends AppException {
  public OperationNotFoundException() {
    super("Operation not found", 5);
  }
}
