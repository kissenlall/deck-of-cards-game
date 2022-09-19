package com.homework.game.model;

import com.homework.game.card.model.Card;
import com.homework.game.card.model.Deck;
import com.homework.game.card.model.Rank;
import com.homework.game.card.model.Suit;
import com.homework.game.player.model.CardPlayer;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @Test
    void shouldReturnUndealtCardCountPerSuit() {

        //Given

        createGame();

        //When

        Map<String, Long> actualResult = game.getUndealtCardCountPerSuit();

        //Then

        assertEquals("{♡=2, ♧=3, ♢=1}", actualResult.toString());
    }

    @Test
    void shouldReturnUndealtCardCountPerSuitAndRank() {

        //Given

        createGame();

        //When

        Map<String, Long> actualResult = game.getUndealtCardCountPerSuitAndRank();

        //Then

        assertEquals("{A♡=2, 3♧=1, 2♧=1, A♧=1, 10♢=1}", actualResult.toString());
    }

    @Test
    void shouldReturnResult() {

        //Given

        createGame();

        //When

        Map<String, Integer> actualResult = game.getResult();

        //Then

        assertEquals(Arrays.asList(20, 1), new ArrayList<>(actualResult.values()));
    }

    @Test
    public void shouldDealCards() {

        //Given

        Card undealtOneOfHeart = new Card(Rank.ACE, Suit.HEART);
        Card undealtThreeOfClub = new Card(Rank.THREE, Suit.CLUB);

        List<Card> cards = new ArrayList<>();
        cards.add(undealtOneOfHeart);
        cards.add(undealtThreeOfClub);

        Deck deck = new Deck(UUID.randomUUID().toString(), cards);

        Map<String, Deck> shoe = new HashMap<>();
        shoe.put(deck.getId(), deck);

        CardPlayer cardPlayer1 = new CardPlayer();
        CardPlayer cardPlayer2 = new CardPlayer();

        Map<String, CardPlayer> players = new HashMap<>();
        players.put(cardPlayer1.getId(), cardPlayer1);
        players.put(cardPlayer2.getId(), cardPlayer2);

        game = new Game(UUID.randomUUID().toString(), shoe, players);

        //When

        game.dealCards();

        //Then

        assertEquals(Status.ENDED, game.getStatus());
        assertTrue(game.getShoe().values().stream().flatMap(d -> d.getCards().stream()).allMatch(Card::isDealt));
        assertEquals(1, cardPlayer1.getHand().size());
        assertEquals(1, cardPlayer2.getHand().size());
        assertNotSame(cardPlayer1.getHand().get(0), cardPlayer2.getHand().get(0));
    }

    private void createGame() {
        Card undealtOneOfHeart = new Card(Rank.ACE, Suit.HEART);
        Card undealtThreeOfClub = new Card(Rank.THREE, Suit.CLUB);
        Card undealtTwoOfClub = new Card(Rank.TWO, Suit.CLUB);
        Card undealtAceOfClub = new Card(Rank.ACE, Suit.CLUB);

        Card dealtQueenOfClub = new Card(Rank.QUEEN, Suit.CLUB);
        dealtQueenOfClub.setDealt(true);

        Card undealtTenOfDiamond = new Card(Rank.TEN, Suit.DIAMOND);

        Card dealtFiveOfSpade = new Card(Rank.FIVE, Suit.SPADE);
        dealtFiveOfSpade.setDealt(true);

        List<Card> cards = new ArrayList<>();
        cards.add(undealtOneOfHeart);
        cards.add(undealtThreeOfClub);
        cards.add(undealtTwoOfClub);
        cards.add(undealtAceOfClub);
        cards.add(dealtQueenOfClub);
        cards.add(undealtTenOfDiamond);
        cards.add(dealtFiveOfSpade);

        Deck deck1 = new Deck(UUID.randomUUID().toString(), cards);

        Card undealtOneOfHeart2 = new Card(Rank.ACE, Suit.HEART);

        List<Card> cards2 = new ArrayList<>();
        cards2.add(undealtOneOfHeart2);

        Deck deck2 = new Deck(UUID.randomUUID().toString(), cards2);

        Map<String, Deck> shoe = new HashMap<>();
        shoe.put(deck1.getId(), deck1);
        shoe.put(deck2.getId(), deck2);

        CardPlayer cardPlayer1 = new CardPlayer();
        cardPlayer1.getHand().add(new Card(Rank.KING, Suit.HEART));
        cardPlayer1.getHand().add(new Card(Rank.SEVEN, Suit.CLUB));

        CardPlayer cardPlayer2 = new CardPlayer();
        cardPlayer2.getHand().add(new Card(Rank.ACE, Suit.DIAMOND));

        Map<String, CardPlayer> players = new HashMap<>();
        players.put(cardPlayer1.getId(), cardPlayer1);
        players.put(cardPlayer2.getId(), cardPlayer2);

        game = new Game(UUID.randomUUID().toString(), shoe, players);
    }
}