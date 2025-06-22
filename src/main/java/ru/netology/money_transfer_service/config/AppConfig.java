package ru.netology.money_transfer_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import ru.netology.money_transfer_service.repository.CardRepository;

@Configuration
public class AppConfig {
    @Bean
    public CardRepository cardRepository(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        return new CardRepository(resourceLoader, objectMapper);
    }
}
