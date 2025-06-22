package ru.netology.money_transfer_service.service;

import org.springframework.stereotype.Service;
import ru.netology.money_transfer_service.dto.ConfirmRequest;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.exception.CardNotFoundException;
import ru.netology.money_transfer_service.exception.InvalidCodeException;
import ru.netology.money_transfer_service.exception.OperationNotFoundException;
import ru.netology.money_transfer_service.model.Card;
import ru.netology.money_transfer_service.model.Operation;
import ru.netology.money_transfer_service.model.OperationStatus;
import ru.netology.money_transfer_service.repository.CardRepository;
import ru.netology.money_transfer_service.repository.OperationRepository;

import java.math.BigDecimal;

@Service
public class ConfirmService {
    private final CardRepository cardRepository;
    private final OperationRepository operationRepository;
    private final LogService logService;

    public ConfirmService(CardRepository cardRepository,
                          OperationRepository operationRepository,
                          LogService logService) {
        this.cardRepository = cardRepository;
        this.operationRepository = operationRepository;
        this.logService = logService;
    }

    public OperationResponse confirm(ConfirmRequest request) {
        Operation operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new OperationNotFoundException());

        if (!"0000".equals(request.getCode())) {
            throw new InvalidCodeException();
        }

        Card cardFrom = cardRepository.findCard(operation.getCardFrom())
                .orElseThrow(() -> new CardNotFoundException());
        Card cardTo = cardRepository.findCard(operation.getCardTo())
                .orElseThrow(() -> new CardNotFoundException());

        BigDecimal totalAmount = operation.getAmount().add(operation.getCommission());

        cardFrom.setBalance(cardFrom.getBalance().subtract(totalAmount));
        cardTo.setBalance(cardTo.getBalance().add(operation.getAmount()));

        operation.setStatus(OperationStatus.SUCCESS.toString());
        operationRepository.save(operation); // Явное сохранение изменений

        logService.logOperation(operation);
        return new OperationResponse(operation.getId());
    }
}
