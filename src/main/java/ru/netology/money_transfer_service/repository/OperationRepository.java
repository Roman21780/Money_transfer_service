package ru.netology.money_transfer_service.repository;

import org.springframework.stereotype.Repository;
import ru.netology.money_transfer_service.model.Operation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OperationRepository {
    private final Map<String, Operation> operations = new ConcurrentHashMap<>();

    public void save(Operation operation) {
        operations.put(operation.getId(), operation);
    }

    public Optional<Operation> findById(String Id) {
        return Optional.ofNullable(operations.get(Id));
    }

    // Метод для обновления статуса операции
    public void updateOperationStatus(String operationId, String status) {
        Operation operation = operations.get(operationId);
        if (operation != null) {
            operation.setStatus(status);
        }
    }
}
