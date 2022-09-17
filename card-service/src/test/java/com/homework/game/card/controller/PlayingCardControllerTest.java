package com.homework.game.card.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.card.model.playingcard.Card;
import com.homework.game.card.model.playingcard.Rank;
import com.homework.game.card.model.playingcard.Suit;
import com.homework.game.card.service.PlayingCardService;
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
class PlayingCardControllerTest {

    @Mock
    private PlayingCardService service;

    @InjectMocks
    private PlayingCardController controller;

    @Test
    void shouldCallServiceToGetDeckOfPlayingCards() {

        //Given

        Deck deck = new Deck(UUID.randomUUID().toString(), Collections.singletonList(new Card(Rank.SEVEN, Suit.CLUB)));
        Mockito.when(service.getDeckOfPlayingCards()).thenReturn(deck);

        //When

        Deck actualDeck = controller.getDeckOfPlayingCards();

        //Then

        Assertions.assertEquals(deck, actualDeck);
    }

}