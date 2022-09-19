package com.homework.game.player.service;

import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.utility.CardPlayerToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PlayerService {

    private final CardPlayerToDtoMapper cardPlayerToDtoMapper;

    //TODO: persist player?

    public CardPlayerDto createCardPlayer() {
        CardPlayer cardPlayer = new CardPlayer();
        cardPlayer.setId(UUID.randomUUID().toString());
        return cardPlayerToDtoMapper.convert(cardPlayer);
    }
}
