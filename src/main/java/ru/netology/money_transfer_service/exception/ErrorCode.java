package ru.netology.money_transfer_service.exception;

import org.springframework.http.HttpStatus;

/**
 * Коды ошибок приложения с HTTP статусами
 */
public enum ErrorCode {
    CARD_NOT_FOUND(1, HttpStatus.NOT_FOUND),
    INSUFFICIENT_FUNDS(2, HttpStatus.BAD_REQUEST),
    INVALID_CODE(3, HttpStatus.BAD_REQUEST),
    INVALID_CARD_DATA(4, HttpStatus.BAD_REQUEST),
    OPERATION_NOT_FOUND(5, HttpStatus.NOT_FOUND),
    CARD_LOAD_ERROR(6, HttpStatus.INTERNAL_SERVER_ERROR);

    private final int id;
    private final HttpStatus httpStatus;

    ErrorCode(int id, HttpStatus httpStatus) {
        this.id = id;
        this.httpStatus = httpStatus;
    }

    public int getId() {
        return id;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}