package com.homework.game.card.service;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.card.model.Deck;
import com.homework.game.card.utility.DeckBuilder;
import com.homework.game.card.utility.DeckToDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeckService {

    private final DeckBuilder deckBuilder;
    private final DeckToDtoMapper deckToDtoMapper;

    //TODO: perhaps save the deck in db?

    public DeckDto createDeckOfPlayingCards() {
        return deckToDtoMapper.convert(deckBuilder.buildPlayingCardsDeck());
    }
}
