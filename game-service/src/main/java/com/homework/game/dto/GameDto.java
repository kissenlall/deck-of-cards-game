package com.homework.game.dto;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.player.dto.CardPlayerDto;
import lombok.Value;

import java.util.Map;

@Value
public class GameDto {
    String id;
    Map<String, DeckDto> shoe;
    Map<String, CardPlayerDto> players;
    String status;
}
