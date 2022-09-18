package com.homework.game.integration;

import com.homework.game.player.model.CardPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
//@Component
public class PlayerServiceProxy {

    private static final String URL = "http://localhost:8082/player-service/player";
    private final RestTemplate restTemplate;

    public CardPlayer createCardPlayer() {
        ResponseEntity<CardPlayer> response = restTemplate.exchange(URL, HttpMethod.GET, null, CardPlayer.class);
        return response.getBody();
    }
}
