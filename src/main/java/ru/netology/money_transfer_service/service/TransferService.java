package ru.netology.money_transfer_service.service;

import org.springframework.stereotype.Service;
import ru.netology.money_transfer_service.dto.OperationResponse;
import ru.netology.money_transfer_service.dto.TransferRequest;
import ru.netology.money_transfer_service.exception.CardNotFoundException;
import ru.netology.money_transfer_service.exception.InsufficientFundsException;
import ru.netology.money_transfer_service.exception.InvalidCardDataException;
import ru.netology.money_transfer_service.model.Card;
import ru.netology.money_transfer_service.model.Operation;
import ru.netology.money_transfer_service.model.OperationStatus;
import ru.netology.money_transfer_service.repository.CardRepository;
import ru.netology.money_transfer_service.repository.OperationRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransferService {
    private final CardRepository cardRepository;
    private final OperationRepository operationRepository;

    public TransferService(CardRepository cardRepository,
                           OperationRepository operationRepository) {
        this.cardRepository = cardRepository;
        this.operationRepository = operationRepository;
    }

    private Card validateCard(String number, String validTill, String cvv) {
        Card card = cardRepository.findCard(number)
                .orElseThrow(CardNotFoundException::new);

        if (!card.validTill().equals(validTill)) {
            throw new InvalidCardDataException("Card expiry date");
        }

        if (!card.cvv().equals(cvv)) {
            throw new InvalidCardDataException("CVV code");
        }

        return card;
    }

    public OperationResponse transfer(TransferRequest request) {
        // Валидация карты отправителя
        Card cardFrom = validateCard(
                request.cardFromNumber(),
                request.cardFromValidTill(),
                request.cardFromCVV()
        );

        // Валидация карты получателя
        Card cardTo = cardRepository.findCard(request.cardToNumber())
                .orElseThrow(CardNotFoundException::new);

        // Проверка баланса
        BigDecimal amount = BigDecimal.valueOf(request.amount().value());
        if (cardFrom.balance().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        // Расчет комиссии (1% от суммы)
        BigDecimal commission = amount.multiply(BigDecimal.valueOf(0.01));

        // Создание операции
        Operation operation = new Operation(
                UUID.randomUUID().toString(),       // id
                cardFrom.number(),               // cardFrom
                cardTo.number(),                 // cardTo
                amount,                             // amount
                commission,                         // commission
                request.amount().currency(),        // currency
                OperationStatus.CREATED.toString(), // status
                "0000"                             // verificationCode
        );

        operationRepository.save(operation);
        return new OperationResponse(operation.id());
    }
}