package com.homework.game.card.model;

import java.util.Arrays;
import java.util.Objects;

public enum Rank {

    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K");

    int value;
    String description;

    Rank(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    //utility

    public static Rank fromValue(int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElse(null);
    }

    public static Rank fromDescription(String description) {
        return Arrays.stream(Rank.values())
                .filter(rank -> Objects.equals(rank.description, description))
                .findFirst()
                .orElse(null);
    }
}
