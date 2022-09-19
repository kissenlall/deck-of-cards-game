package com.homework.game.controller;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.card.model.Deck;
import com.homework.game.dto.GameDto;
import com.homework.game.enums.Grouping;
import com.homework.game.model.Game;
import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.player.model.CardPlayer;
import com.homework.game.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private GameService service;

    @InjectMocks
    private GameController controller;

    @Test
    void shouldCallServiceToCreateGame() {

        //Given

        GameDto expectedGame = new GameDto(null, null, null, null);
        Mockito.when(service.createGame()).thenReturn(expectedGame);

        //When

        GameDto actualGame = controller.createGame();

        //Then

        Assertions.assertEquals(expectedGame, actualGame);
    }

    @Test
    void shouldCallServiceToDeleteGame() {

        //Given

        Mockito.doNothing().when(service).deleteGame("id");

        //When

       controller.deleteGame("id");

        //Then

        Mockito.verify(service).deleteGame("id");
    }

    @Test
    void shouldCallServiceToAddDeckToGame() {

        //Given

        GameDto expectedGame = new GameDto(null, null, null, null);
        DeckDto deck = new DeckDto(null, null);

        Mockito.when(service.addDeckToGame("id", deck)).thenReturn(expectedGame);

        //When

        GameDto actualGame = controller.addDeckToGame("id", deck);

        //Then

        Assertions.assertEquals(expectedGame, actualGame);
    }

    @Test
    void shouldCallServiceToAddPlayerToGame() {

        //Given

        GameDto expectedGame = new GameDto(null, null, null, null);
        CardPlayerDto player = new CardPlayerDto(null, null);

        Mockito.when(service.addPlayerToGame("id", player)).thenReturn(expectedGame);

        //When

        GameDto actualGame = controller.addPlayerToGame("id", player);

        //Then

        Assertions.assertEquals(expectedGame, actualGame);
    }

    @Test
    void shouldCallServiceToRemovePlayerFromGame() {

        //Given

        GameDto expectedGame = new GameDto(null, null, null, null);

        Mockito.when(service.removePlayer("id", "playerId")).thenReturn(expectedGame);

        //When

        GameDto actualGame = controller.removePlayerFromGame("id", "playerId");

        //Then

        Assertions.assertEquals(expectedGame, actualGame);
    }

    @Test
    void shouldCallServiceToDealCards() {

        //Given

        GameDto expectedGame = new GameDto(null, null, null, null);

        Mockito.when(service.dealCards("id")).thenReturn(expectedGame);

        //When

        GameDto actualGame = controller.dealCards("id");

        //Then

        Assertions.assertEquals(expectedGame, actualGame);
    }

    @Test
    void shouldCallServiceToShuffleCards() {

        //Given

        GameDto expectedGame = new GameDto(null, null, null, null);

        Mockito.when(service.shuffleCards("id")).thenReturn(expectedGame);

        //When

        GameDto actualGame = controller.shuffleCards("id");

        //Then

        Assertions.assertEquals(expectedGame, actualGame);
    }

    @Test
    void shouldCallServiceToGetPlayerCards() {

        //Given

        List<String> playerCards = new ArrayList<>();

        Mockito.when(service.getPlayerCards("id", "playerId")).thenReturn(playerCards);

        //When

        List<String> actualPlayerCards = controller.getPlayerCards("id", "playerId");

        //Then

        Assertions.assertEquals(playerCards, actualPlayerCards);
    }

    @Test
    void shouldCallServiceToGetGameResult() {

        //Given

        Map<String, Integer> gameResult = new HashMap<>();

        Mockito.when(service.getResult("id")).thenReturn(gameResult);

        //When

        Map<String, Integer> actualResult = controller.getGameResult("id");

        //Then

        Assertions.assertEquals(gameResult, actualResult);
    }

    @Test
    void shouldCallServiceToGetRemaingCardCount() {

        //Given

        Map<String, Long> remainingCardCount = new HashMap<>();

        Mockito.when(service.getRemainingCardsCount("id", Grouping.SUIT_ONLY)).thenReturn(remainingCardCount);

        //When

        Map<String, Long> actualResult = controller.getRemainingGameCards("id", Grouping.SUIT_ONLY);

        //Then

        Assertions.assertEquals(remainingCardCount, actualResult);
    }
}