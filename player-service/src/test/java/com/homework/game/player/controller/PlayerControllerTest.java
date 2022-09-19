package com.homework.game.player.controller;

import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.player.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private PlayerService service;

    @InjectMocks
    private PlayerController controller;

    @Test
    void shouldCallServiceToCreateNewPlayer() {

        //Given

        CardPlayerDto player = new CardPlayerDto("id", null);
        Mockito.when(service.createCardPlayer()).thenReturn(player);

        //When

        CardPlayerDto actualPlayer = controller.createCardPlayer();

        //Then

        Assertions.assertEquals(player, actualPlayer);
    }

}