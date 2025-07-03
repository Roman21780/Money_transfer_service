package ru.netology.money_transfer_service.exception;

import org.springframework.http.HttpStatus;

/**
 * Базовое исключение приложения
 */
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getId() {
        return errorCode.getId();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }

    public String getErrorCode() {
        return errorCode.name();
    }
}