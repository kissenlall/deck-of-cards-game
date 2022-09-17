package com.homework.game.card.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.card.service.PlayingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlayingCardController {

    private final PlayingCardService service;

    @GetMapping(value = "/playing-cards/deck", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Deck getDeckOfPlayingCards() {
        return service.getDeckOfPlayingCards();
    }
}
