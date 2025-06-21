package ru.netology.money_transfer_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer_service.dto.ConfirmRequest;
import ru.netology.money_transfer_service.dto.ErrorResponse;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.exception.AppException;
import ru.netology.money_transfer_service.service.ConfirmService;

@RestController
@RequestMapping("/confirmOperation")
public class ConfirmController {
    @Autowired private ConfirmService confirmService;

    // Конструктор с внедрением зависимости
    public ConfirmController(ConfirmService confirmService) {
        this.confirmService = confirmService;
    }

    @PostMapping
    public ResponseEntity<?> confirm(@RequestBody @Valid ConfirmRequest request) {
        try {
            OperationResponse response = confirmService.confirm(request);
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), e.getId()));
        }
    }
}
