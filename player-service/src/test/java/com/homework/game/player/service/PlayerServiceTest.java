package com.homework.game.player.service;

import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    private PlayerService service;

    @Test
    void shouldReturnNewCardPlayer() {

        //Given

        service = new PlayerService();

        //When

        Player actualPlayer = service.createCardPlayer();

        //Then

        assertNotNull(actualPlayer);
        assertNotNull(actualPlayer.getId());
        assertTrue(actualPlayer instanceof CardPlayer);
        assertTrue(((CardPlayer) actualPlayer).getHand().isEmpty());
    }

}