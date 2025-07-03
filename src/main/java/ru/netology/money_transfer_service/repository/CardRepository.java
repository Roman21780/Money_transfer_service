package ru.netology.money_transfer_service.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import ru.netology.money_transfer_service.exception.CardLoadException;
import ru.netology.money_transfer_service.model.Card;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {
    @Value("${cards.file-path}")
    private String cardsFilePath;
    public static final Logger log = LogManager.getLogger(CardRepository.class);

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
        } catch (CardLoadException e) {
            log.error("Failed to load cards from JSON file", e);
            throw new IllegalStateException("Cannot start application without cards data", e);
        }
    }

    public void loadCards() throws CardLoadException {
        try {
            Resource resource = resourceLoader.getResource(cardsFilePath);
            try (InputStream is = resource.getInputStream()) {
                List<Card> cardList = objectMapper.readValue(is, new TypeReference<>() {});
                cardList.forEach(card -> cards.put(card.number(), card));
            }
        } catch (IOException e) {
            log.error("Failed to load cards from file: {}", cardsFilePath, e);
            throw new CardLoadException("Failed to load cards data", e);
        }
    }

    public Optional<Card> findCard(String number) {
        return Optional.ofNullable(cards.get(number));
    }

    /**
     * Сохраняет новую версию карты
     * @param card Новая версия карты
     * @return Сохраненная карта
     */
    public Card save(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        cards.put(card.number(), card);
        log.debug("Card {} saved/updated", card.number());
        return card;
    }

    /**
     * Обновляет баланс карты
     * @param cardNumber Номер карты
     * @param newBalance Новый баланс
     * @return Обновленная карта или empty, если карта не найдена
     */
    public Optional<Card> updateBalance(String cardNumber, BigDecimal newBalance) {
        return findCard(cardNumber)
                .map(card -> {
                    Card updatedCard = card.withBalance(newBalance);
                    return save(updatedCard);
                });
    }
}