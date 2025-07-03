package ru.netology.money_transfer_service.repository;

import ru.netology.money_transfer_service.model.Operation;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class OperationRepository {
    private final Map<String, Operation> operations = new ConcurrentHashMap<>();

    public void save(Operation operation) {
        operations.put(operation.id(), operation);
    }

    public Optional<Operation> findById(String id) {
        return Optional.ofNullable(operations.get(id));
    }

    public void updateStatus(String operationId, String status) {
        operations.computeIfPresent(operationId, (id, op) -> op.withStatus(status));
    }
}