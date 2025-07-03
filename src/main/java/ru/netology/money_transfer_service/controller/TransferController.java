package ru.netology.money_transfer_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.dto.TransferRequest;
import ru.netology.money_transfer_service.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<OperationResponse> transfer(@RequestBody @Valid TransferRequest request) {
        OperationResponse response = transferService.transfer(request);
        return ResponseEntity.ok(response);
    }
}
