package com.homework.game.player.dto;

import lombok.Value;

import java.util.List;

@Value
public class CardPlayerDto {
    String id;
    List<String> cards;
}
