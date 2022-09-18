package com.homework.game.card.util;

import com.homework.game.card.model.Deck;
import com.homework.game.card.model.Card;
import com.homework.game.card.model.Rank;
import com.homework.game.card.model.Suit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class DeckBuilder {

    public Deck buildPlayingCardsDeck() {
        List<Card> cards = new ArrayList<>(52);
        for(int value = 1 ; value <= 13 ; value++){
            for(Suit suit : Suit.values()){
                cards.add(new Card(Rank.fromValue(value),suit));
            }
        }
        log.info("Creating deck with : [{}]", cards);
        return new Deck(UUID.randomUUID().toString(), cards);
    }
}
