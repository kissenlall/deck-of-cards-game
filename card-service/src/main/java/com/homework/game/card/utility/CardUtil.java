package com.homework.game.card.utility;

import com.homework.game.card.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardUtil {

    private static final int SUIT_INDEX = 1;
    private static final int RANK_INDEX = 0;

    public String toString(Card card) {
       return String.format("[%s|%s]", card.getRank().getDescription(), card.getSuit().getValue());
    }

    public String toRank(String card) {
        return splitValue(card, RANK_INDEX);
    }

    public String toSuit(String card) {
        return splitValue(card, SUIT_INDEX);
    }

    private String splitValue(String card, int indexToReturn) {
        return card != null ? card.replace("[", "").replace("]", "").split("\\|")[indexToReturn] : null;
    }
}
