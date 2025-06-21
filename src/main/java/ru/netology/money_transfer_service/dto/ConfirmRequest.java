package ru.netology.money_transfer_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ConfirmRequest {
    @JsonProperty("operationId")
    private String operationId;

    @JsonProperty("code")
    private String code;

    // Геттеры и сеттеры
    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmRequest that = (ConfirmRequest) o;
        return Objects.equals(operationId, that.operationId) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, code);
    }
}
