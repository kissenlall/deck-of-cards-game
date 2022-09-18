package com.homework.game.player.controller;

import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.model.Player;
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

        CardPlayer player = new CardPlayer();
        Mockito.when(service.createCardPlayer()).thenReturn(player);

        //When

        Player actualPlayer = controller.createCardPlayer();

        //Then

        Assertions.assertEquals(player, actualPlayer);
    }

}