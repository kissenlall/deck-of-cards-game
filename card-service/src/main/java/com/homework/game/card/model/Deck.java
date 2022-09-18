package com.homework.game.card.model;

import lombok.Value;

import java.util.List;

@Value
public class Deck {
   String id;
   List<Card> cards;
}
