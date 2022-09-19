package com.homework.game.card.controller;

import com.homework.game.card.dto.DeckDto;
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

        DeckDto deck = new DeckDto(UUID.randomUUID().toString(), Collections.singletonList("[A|♡]"));
        Mockito.when(service.createDeckOfPlayingCards()).thenReturn(deck);

        //When

        DeckDto actualDeck = controller.createDeckOfPlayingCards();

        //Then

        Assertions.assertEquals(deck, actualDeck);
    }

}