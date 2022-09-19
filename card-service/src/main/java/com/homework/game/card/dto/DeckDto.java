package com.homework.game.card.dto;

import lombok.Value;

import java.util.List;

@Value
public class DeckDto {
    String id;
    List<String> cards;
}
