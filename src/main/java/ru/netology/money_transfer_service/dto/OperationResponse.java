package ru.netology.money_transfer_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OperationResponse {
    @JsonProperty("operationId")
    private String operationId;

    // Конструктор
    public OperationResponse(String operationId) {
            this.operationId = operationId;
    }

    // Геттеры и сеттеры
    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationResponse that = (OperationResponse) o;
        return Objects.equals(operationId, that.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }
}
