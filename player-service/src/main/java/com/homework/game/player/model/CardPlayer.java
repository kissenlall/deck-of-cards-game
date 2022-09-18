package com.homework.game.player.model;

import com.homework.game.card.model.AbstractCard;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class CardPlayer extends Player {

    private final List<AbstractCard> hand;

    public CardPlayer() {
        super(UUID.randomUUID().toString());
        hand = new ArrayList<>();
    }

}