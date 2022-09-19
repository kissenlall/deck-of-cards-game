package com.homework.game.utility;

import com.homework.game.card.model.Card;
import com.homework.game.card.model.Deck;
import com.homework.game.card.model.Rank;
import com.homework.game.card.model.Suit;
import com.homework.game.model.Game;
import com.homework.game.model.Status;
import com.homework.game.player.model.CardPlayer;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameUtilTest {

    private GameUtil util;
    private Game game;

    @Test
    void shouldReturnRemainingCardCountPerSuit() {

        //Given

        util = new GameUtil();
        game = createGame();

        //When

        Map<String, Long> actualResult = util.getRemainingCardCountPerSuit(game);

        //Then

        assertEquals("{[♡]=2, [♧]=3, [♢]=1}", actualResult.toString());
    }

    @Test
    void shouldReturnRemainingCardCountPerSuitAndRank() {

        //Given

        util = new GameUtil();
        game = createGame();

        //When

        Map<String, Long> actualResult = util.getRemainingCardCountPerSuitAndRank(game);

        //Then

        assertEquals("{[A|♡]=2, [3|♧]=1, [2|♧]=1, [A|♧]=1, [10|♢]=1}", actualResult.toString());
    }

    @Test
    void shouldReturnResult() {

        //Given

        util = new GameUtil();
        game = createGame();

        //When

        Map<String, Integer> actualResult = util.getResult(game);

        //Then

        assertEquals(Arrays.asList(20, 1), new ArrayList<>(actualResult.values()));
    }

    @Test
    public void shouldDealCards() {

        //Given

        util = new GameUtil();

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.HEART, false));
        cards.add(new Card(Rank.THREE, Suit.CLUB, false));

        Deck deck = new Deck(UUID.randomUUID().toString(), cards);

        Map<String, Deck> shoe = new HashMap<>();
        shoe.put(deck.getId(), deck);

        CardPlayer cardPlayer1 = new CardPlayer();
        cardPlayer1.setId(UUID.randomUUID().toString());

        CardPlayer cardPlayer2 = new CardPlayer();
        cardPlayer2.setId(UUID.randomUUID().toString());

        Map<String, CardPlayer> players = new HashMap<>();
        players.put(cardPlayer1.getId(), cardPlayer1);
        players.put(cardPlayer2.getId(), cardPlayer2);

        game = new Game(shoe, players, Status.NOT_STARTED);

        //When

        util.dealCards(game);

        //Then

        assertTrue(game.getShoe().values().stream().flatMap(d -> d.getCards().stream()).allMatch(Card::isDealt));
        assertEquals(1, cardPlayer1.getHand().size());
        assertEquals(1, cardPlayer2.getHand().size());
        assertNotSame(cardPlayer1.getHand().get(0), cardPlayer2.getHand().get(0));
    }


    private Game createGame() {

        Deck deck1 = createDeck1();
        Deck deck2 = createDeck2();

        Map<String, Deck> shoe = new HashMap<>();
        shoe.put(deck1.getId(), deck1);
        shoe.put(deck2.getId(), deck2);

        CardPlayer cardPlayer1 = new CardPlayer();
        cardPlayer1.setId(UUID.randomUUID().toString());
        cardPlayer1.getHand().add(new Card(Rank.KING, Suit.HEART, true));
        cardPlayer1.getHand().add(new Card(Rank.SEVEN, Suit.CLUB, true));

        CardPlayer cardPlayer2 = new CardPlayer();
        cardPlayer2.setId(UUID.randomUUID().toString());
        cardPlayer2.getHand().add(new Card(Rank.ACE, Suit.DIAMOND, true));

        Map<String, CardPlayer> players = new HashMap<>();
        players.put(cardPlayer1.getId(), cardPlayer1);
        players.put(cardPlayer2.getId(), cardPlayer2);

        return new Game(shoe, players, Status.ENDED);
    }

    private Deck createDeck2() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.HEART, false));
        return new Deck(UUID.randomUUID().toString(), cards);
    }

    private Deck createDeck1() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.HEART, false));
        cards.add(new Card(Rank.THREE, Suit.CLUB, false));
        cards.add(new Card(Rank.TWO, Suit.CLUB, false));
        cards.add(new Card(Rank.ACE, Suit.CLUB, false));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB, true));
        cards.add(new Card(Rank.TEN, Suit.DIAMOND, false));
        cards.add(new Card(Rank.FIVE, Suit.SPADE, true));
        return new Deck(UUID.randomUUID().toString(), cards);
    }
}