package com.homework.game.player.service;

import com.homework.game.player.model.CardPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    private PlayerService service;

    @Test
    void shouldReturnNewCardPlayer() {

        //Given

        service = new PlayerService();

        //When

        CardPlayer actualPlayer = service.createCardPlayer();

        //Then

        assertNotNull(actualPlayer);
        assertNotNull(actualPlayer.getId());
        assertTrue(actualPlayer.getHand().isEmpty());
    }

}