package com.homework.game.integration;

import com.homework.game.card.model.Deck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
//@Component
public class CardServiceProxy {

    private static final String URL = "http://localhost:8081/deck/playing-cards";
    private final RestTemplate restTemplate;

    public Deck createDeckOfPlayingCards() {
        ResponseEntity<Deck> response = restTemplate.exchange(URL, HttpMethod.GET, null, Deck.class);
        return response.getBody();
    }
}
