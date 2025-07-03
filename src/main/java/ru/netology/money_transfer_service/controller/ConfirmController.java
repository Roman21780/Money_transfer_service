package ru.netology.money_transfer_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer_service.dto.ConfirmRequest;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.service.ConfirmService;

@RestController
@RequestMapping("/confirmOperation")
public class ConfirmController {
    private final ConfirmService confirmService;

    public ConfirmController(ConfirmService confirmService) {
        this.confirmService = confirmService;
    }

    @PostMapping
    public ResponseEntity<OperationResponse> confirm(@RequestBody @Valid ConfirmRequest request) {
        OperationResponse response = confirmService.confirm(request);
        return ResponseEntity.ok(response);
    }
}
