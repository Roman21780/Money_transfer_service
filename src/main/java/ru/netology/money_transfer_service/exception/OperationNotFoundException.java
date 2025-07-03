package ru.netology.money_transfer_service.exception;

/**
 * Исключение при отсутствии операции
 */
public class OperationNotFoundException extends AppException {
  public OperationNotFoundException() {
    super("Operation not found", ErrorCode.OPERATION_NOT_FOUND);
  }
}