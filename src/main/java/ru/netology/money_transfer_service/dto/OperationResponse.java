package ru.netology.money_transfer_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Ответ с идентификатором операции
 *
 * @param operationId Уникальный идентификатор операции
 */
public record OperationResponse(
        @JsonProperty("operationId")
        String operationId
) {
    // Дополнительные методы можно добавить при необходимости
    // Например, метод для создания копии с новым ID
    public OperationResponse withOperationId(String newOperationId) {
        return new OperationResponse(newOperationId);
    }

    // equals и hashCode генерируются автоматически
    // toString также генерируется автоматически
}