package com.homework.game.player.service;

import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.utility.CardPlayerToDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private CardPlayerToDtoMapper cardPlayerToDtoMapper;

    @InjectMocks
    private PlayerService service;

    @Test
    void shouldReturnNewCardPlayer() {

        //Given

        Mockito.when(cardPlayerToDtoMapper.convert(Mockito.any(CardPlayer.class))).thenReturn(new CardPlayerDto("id", null));

        //When

        CardPlayerDto actualPlayer = service.createCardPlayer();

        //Then

        assertNotNull(actualPlayer);
        assertNotNull(actualPlayer.getId());
        assertNull(actualPlayer.getCards());
    }

}