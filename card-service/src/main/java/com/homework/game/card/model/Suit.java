package com.homework.game.card.model;

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
}
