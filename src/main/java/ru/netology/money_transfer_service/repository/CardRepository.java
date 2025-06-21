package ru.netology.money_transfer_service.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import ru.netology.money_transfer_service.model.Card;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class CardRepository {
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final ResourceLoader resourceLoader;

    public CardRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadCards() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource("classpath:cards.json");
        try (InputStream is = resource.getInputStream()) {
            List<Card> cardList = mapper.readValue(is, new TypeReference<List<Card>>() {});
            cardList.forEach(card -> cards.put(card.getNumber(), card));
        }
    }

    public Optional<Card> findCard(String number) {
        return Optional.ofNullable(cards.get(number));
    }
}