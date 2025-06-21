package ru.netology.money_transfer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import ru.netology.money_transfer_service.repository.CardRepository;

import java.io.IOException;

@Configuration
public class AppConfig {
    @Bean
    public CardRepository cardRepository(ResourceLoader resourceLoader) throws IOException {
        CardRepository repo = new CardRepository(resourceLoader);
        repo.loadCards(); // Загрузка карт из cards.json
        return repo;
    }
}
