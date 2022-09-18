package com.homework.game.card.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.card.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeckController {

    private final DeckService service;

    @GetMapping(value = "/deck/playing-cards", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED )
    public Deck createDeckOfPlayingCards() {
        return service.createDeckOfPlayingCards();
    }
}
