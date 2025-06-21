package ru.netology.money_transfer_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class ErrorResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("id")
    private int id;

    @JsonProperty("timestamp")
    private long timestamp;

    // Конструктор
    public ErrorResponse(String message, int id) {
        this.message = message;
        this.id = id;
        this.timestamp = System.currentTimeMillis();
    }

    // Геттеры и сеттеры
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return id == that.id &&
                timestamp == that.timestamp &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, id, timestamp);
    }
}
