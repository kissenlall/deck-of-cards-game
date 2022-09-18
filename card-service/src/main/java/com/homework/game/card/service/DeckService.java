package com.homework.game.card.service;

import com.homework.game.card.model.Deck;
import com.homework.game.card.util.DeckBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeckService {

    private final DeckBuilder deckBuilder;

    //TODO: persist deck of cards?

    public Deck createDeckOfPlayingCards() {
        return deckBuilder.buildPlayingCardsDeck();
    }
}
