package ru.netology.money_transfer_service.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.money_transfer_service.dto.ErrorResponse;


import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка кастомных исключений
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getId());
        return ResponseEntity.badRequest().body(response);
    }

    // Обработка стандартных исключений валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Validation error: " + errorMessage, 400));
    }

    // Обработка всех остальных исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse("Internal server error", 500));
    }
}
