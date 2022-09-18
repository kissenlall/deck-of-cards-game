package com.homework.game.card.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.card.model.Card;
import com.homework.game.card.model.Rank;
import com.homework.game.card.model.Suit;
import com.homework.game.card.service.DeckService;
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
class DeckControllerTest {

    @Mock
    private DeckService service;

    @InjectMocks
    private DeckController controller;

    @Test
    void shouldCallServiceToGetDeckOfPlayingCards() {

        //Given

        Deck deck = new Deck(UUID.randomUUID().toString(), Collections.singletonList(new Card(Rank.SEVEN, Suit.CLUB)));
        Mockito.when(service.createDeckOfPlayingCards()).thenReturn(deck);

        //When

        Deck actualDeck = controller.createDeckOfPlayingCards();

        //Then

        Assertions.assertEquals(deck, actualDeck);
    }

}