package com.homework.game.card.service;

import com.homework.game.card.model.Deck;
import com.homework.game.card.util.DeckBuilder;
import com.homework.game.card.model.playingcard.Card;
import com.homework.game.card.model.playingcard.Rank;
import com.homework.game.card.model.playingcard.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PlayingCardServiceTest {

    @Mock
    private DeckBuilder deckBuilder;

    @InjectMocks
    private PlayingCardService service;

    @Test
    void shouldCallDeckBuilderToGetDeckOfPlayingCards() {

        //Given

        Deck deck = new Deck(UUID.randomUUID().toString(), Collections.singletonList(new Card(Rank.SEVEN, Suit.CLUB)));
        Mockito.when(deckBuilder.buildPlayingCardsDeck()).thenReturn(deck);

        //When

        Deck actualDeck = service.getDeckOfPlayingCards();

        //Then

        Assertions.assertEquals(deck, actualDeck);
    }
}