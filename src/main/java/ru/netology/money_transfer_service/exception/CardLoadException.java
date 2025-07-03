package ru.netology.money_transfer_service.exception;

import org.springframework.http.HttpStatus;

public class CardLoadException extends AppException {
  public CardLoadException(String message, Throwable cause) {
    super(message, ErrorCode.CARD_LOAD_ERROR, cause);
  }
}