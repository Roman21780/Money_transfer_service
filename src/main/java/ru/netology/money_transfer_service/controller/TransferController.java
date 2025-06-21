package ru.netology.money_transfer_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer_service.dto.ErrorResponse;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.dto.TransferRequest;
import ru.netology.money_transfer_service.exception.AppException;
import ru.netology.money_transfer_service.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired private TransferService transferService;

    // Конструктор с внедрением зависимости
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody @Valid TransferRequest request) {
        try {
            OperationResponse response = transferService.transfer(request);
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), e.getId()));
        }
    }
}
