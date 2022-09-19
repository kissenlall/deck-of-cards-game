package com.homework.game.card.model;

import java.util.Arrays;
import java.util.Objects;

public enum Suit {

    HEART("♡"),
    SPADE("♤"),
    CLUB("♧"),
    DIAMOND("♢");

    final String value;

    Suit(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

    //utility

    public static Suit fromValue(String value) {
        return Arrays.stream(values())
                .filter(suit -> Objects.equals(suit.value, value))
                .findFirst()
                .orElse(null);
    }
}
