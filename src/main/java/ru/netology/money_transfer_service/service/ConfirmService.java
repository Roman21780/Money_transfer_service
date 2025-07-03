package ru.netology.money_transfer_service.service;

import org.springframework.stereotype.Service;
import ru.netology.money_transfer_service.dto.ConfirmRequest;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.exception.*;
import ru.netology.money_transfer_service.model.*;
import ru.netology.money_transfer_service.repository.*;

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
        Operation operation = operationRepository.findById(request.operationId())
                .orElseThrow(OperationNotFoundException::new);

        if (!"0000".equals(request.code())) {
            throw new InvalidConfirmationCodeException();
        }

        Card cardFrom = cardRepository.findCard(operation.cardFrom())
                .orElseThrow(CardNotFoundException::new);
        Card cardTo = cardRepository.findCard(operation.cardTo())
                .orElseThrow(CardNotFoundException::new);

        BigDecimal totalAmount = operation.amount().add(operation.commission());

        // Создаем новые версии карт с обновленными балансами
        Card updatedCardFrom = cardFrom.withBalance(cardFrom.balance().subtract(totalAmount));
        Card updatedCardTo = cardTo.withBalance(cardTo.balance().add(operation.amount()));

        // Обновляем операцию
        Operation updatedOperation = operation.withStatus(OperationStatus.SUCCESS.toString());

        // Сохраняем изменения
        cardRepository.save(updatedCardFrom);
        cardRepository.save(updatedCardTo);
        operationRepository.save(updatedOperation);

        logService.logOperation(updatedOperation);
        return new OperationResponse(updatedOperation.id());
    }
}