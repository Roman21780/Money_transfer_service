package ru.netology.money_transfer_service.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import ru.netology.money_transfer_service.model.Card;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {
    private static final Logger log = LogManager.getLogger(CardRepository.class);

    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public CardRepository(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        try {
            loadCards();
            log.info("Successfully loaded {} cards", cards.size());
        } catch (IOException e) {
            log.error("Failed to load cards from JSON file", e);
            throw new IllegalStateException("Cannot start application without cards data", e);
        }
    }

    public void loadCards() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:cards.json");
        try (InputStream is = resource.getInputStream()) {
            List<Card> cardList = objectMapper.readValue(is, new TypeReference<>() {});
            cardList.forEach(card -> cards.put(card.getNumber(), card));
        }
    }

    public Optional<Card> findCard(String number) {
        return Optional.ofNullable(cards.get(number));
    }
}