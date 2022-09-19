package com.homework.game.card.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Serializable {
    private Rank rank;
    private Suit suit;
    private boolean dealt;
}
