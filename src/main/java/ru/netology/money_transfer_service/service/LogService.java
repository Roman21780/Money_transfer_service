package ru.netology.money_transfer_service.service;

import org.springframework.stereotype.Service;
import ru.netology.money_transfer_service.model.Operation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogService {
    private static final String LOG_FILE = "transfers.log";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logOperation(Operation operation) {
        String logEntry = String.format(
                "[%s] From: %s To: %s Amount: %s %s Commission: %s Result: %s\n",
                LocalDateTime.now().format(DATE_FORMAT),
                operation.getCardFrom(),
                operation.getCardTo(),
                operation.getAmount(),
                operation.getCurrency(),
                operation.getCommission(),
                operation.getStatus()
        );

        // Запись в файл (с синхронизацией)
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
