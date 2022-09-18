package com.homework.game.player.model;

import lombok.Getter;

@Getter
public abstract class Player {

    private final String id;

    public Player(String id) {
        this.id = id;
    }
}
