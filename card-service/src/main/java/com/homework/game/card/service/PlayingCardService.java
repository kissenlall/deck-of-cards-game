package com.homework.game.card.service;

import com.homework.game.card.model.Deck;
import com.homework.game.card.util.DeckBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PlayingCardService {

    private final DeckBuilder deckBuilder;

    public Deck getDeckOfPlayingCards() {
        return deckBuilder.buildPlayingCardsDeck();
    }
}
