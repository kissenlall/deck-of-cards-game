package com.homework.game.card.utility;

import com.homework.game.card.model.Deck;
import com.homework.game.card.model.Card;
import com.homework.game.card.model.Rank;
import com.homework.game.card.model.Suit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DeckBuilder {

    public Deck buildPlayingCardsDeck() {
        List<Card> cards = new ArrayList<>(52);
        for(int value = 1 ; value <= 13 ; value++){
            for(Suit suit : Suit.values()){
                cards.add(new Card(Rank.fromValue(value),suit, false));
            }
        }
        return new Deck(UUID.randomUUID().toString(), cards);
    }
}
