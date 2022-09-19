package com.homework.game.card.model;

import com.homework.game.card.utility.DeckBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class DeckBuilderTest {

    private DeckBuilder deckBuilder;

    @Test
    void shouldBuildPlayingCardDeck() {

        //Given

        deckBuilder = new DeckBuilder();

        //When

        Deck actualDeck = deckBuilder.buildPlayingCardsDeck();

        //Then

        Assertions.assertNotNull(actualDeck.getId());
        Assertions.assertEquals(expectedDeck().getCards(), actualDeck.getCards());
    }

    private Deck expectedDeck() {
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Rank.ACE, Suit.HEART, false));
        cards.add(new Card(Rank.ACE, Suit.SPADE, false));
        cards.add(new Card(Rank.ACE, Suit.CLUB, false));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND, false));

        cards.add(new Card(Rank.TWO, Suit.HEART, false));
        cards.add(new Card(Rank.TWO, Suit.SPADE, false));
        cards.add(new Card(Rank.TWO, Suit.CLUB, false));
        cards.add(new Card(Rank.TWO, Suit.DIAMOND, false));

        cards.add(new Card(Rank.THREE, Suit.HEART, false));
        cards.add(new Card(Rank.THREE, Suit.SPADE, false));
        cards.add(new Card(Rank.THREE, Suit.CLUB, false));
        cards.add(new Card(Rank.THREE, Suit.DIAMOND, false));

        cards.add(new Card(Rank.FOUR, Suit.HEART, false));
        cards.add(new Card(Rank.FOUR, Suit.SPADE, false));
        cards.add(new Card(Rank.FOUR, Suit.CLUB, false));
        cards.add(new Card(Rank.FOUR, Suit.DIAMOND, false));

        cards.add(new Card(Rank.FIVE, Suit.HEART, false));
        cards.add(new Card(Rank.FIVE, Suit.SPADE, false));
        cards.add(new Card(Rank.FIVE, Suit.CLUB, false));
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND, false));

        cards.add(new Card(Rank.SIX, Suit.HEART, false));
        cards.add(new Card(Rank.SIX, Suit.SPADE, false));
        cards.add(new Card(Rank.SIX, Suit.CLUB, false));
        cards.add(new Card(Rank.SIX, Suit.DIAMOND, false));

        cards.add(new Card(Rank.SEVEN, Suit.HEART, false));
        cards.add(new Card(Rank.SEVEN, Suit.SPADE, false));
        cards.add(new Card(Rank.SEVEN, Suit.CLUB, false));
        cards.add(new Card(Rank.SEVEN, Suit.DIAMOND, false));

        cards.add(new Card(Rank.EIGHT, Suit.HEART, false));
        cards.add(new Card(Rank.EIGHT, Suit.SPADE, false));
        cards.add(new Card(Rank.EIGHT, Suit.CLUB, false));
        cards.add(new Card(Rank.EIGHT, Suit.DIAMOND, false));

        cards.add(new Card(Rank.NINE, Suit.HEART, false));
        cards.add(new Card(Rank.NINE, Suit.SPADE, false));
        cards.add(new Card(Rank.NINE, Suit.CLUB, false));
        cards.add(new Card(Rank.NINE, Suit.DIAMOND, false));

        cards.add(new Card(Rank.TEN, Suit.HEART, false));
        cards.add(new Card(Rank.TEN, Suit.SPADE, false));
        cards.add(new Card(Rank.TEN, Suit.CLUB, false));
        cards.add(new Card(Rank.TEN, Suit.DIAMOND, false));

        cards.add(new Card(Rank.JACK, Suit.HEART, false));
        cards.add(new Card(Rank.JACK, Suit.SPADE, false));
        cards.add(new Card(Rank.JACK, Suit.CLUB, false));
        cards.add(new Card(Rank.JACK, Suit.DIAMOND, false));

        cards.add(new Card(Rank.QUEEN, Suit.HEART, false));
        cards.add(new Card(Rank.QUEEN, Suit.SPADE, false));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB, false));
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND, false));

        cards.add(new Card(Rank.KING, Suit.HEART, false));
        cards.add(new Card(Rank.KING, Suit.SPADE, false));
        cards.add(new Card(Rank.KING, Suit.CLUB, false));
        cards.add(new Card(Rank.KING, Suit.DIAMOND, false));

        return new Deck(UUID.randomUUID().toString(), cards);
    }

}