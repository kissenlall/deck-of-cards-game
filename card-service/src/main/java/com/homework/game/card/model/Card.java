package com.homework.game.card.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Card {
    private final Rank rank;
    private final Suit suit;

    @Setter
    private boolean dealt = false;
}
