package com.homework.game.utility;

import com.homework.game.card.model.Card;
import com.homework.game.card.model.Deck;
import com.homework.game.model.Game;
import com.homework.game.player.model.CardPlayer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GameUtil {

    /**
     * This method is used to shuffle the cards per deck, i.e changing their
     * position to somewhere else in the deck, determined by the random number
     * generator.
     *
     * @param game
     */
    public void shuffleCards(Game game) {
        if(!game.hasAnyDeck()) return;
        Random r = new Random();
        for (Deck deck : game.getShoe().values()) {
            int deckSize = deck.getCards().size();
            for (int i = deckSize - 1; i > 0; i--) {
                int j = r.nextInt(i);
                Collections.swap(deck.getCards(), i, j);
            }
        }
    }

    /**
     * This method shuffles all the cards in the game deck and allocates them equally among all the
     * players. There will be some remaining undealt cards in some cases.
     *
     * @param game
     */
    public void dealCards(Game game) {

        if(!game.hasAnyDeck() || !game.hasAnyPlayers()) return;

        shuffleCards(game);

        List<Card> cards = game.getShoe()
                .values()
                .stream()
                .flatMap(s -> s.getCards().stream())
                .collect(Collectors.toList());

        int numberOfPlayers = game.getPlayers().size();
        int numberOfCards = cards.size();
        int numberOfCardsPerPlayer = numberOfCards / numberOfPlayers;

        int fromIndex = 0; //inclusive
        int toIndex = numberOfCardsPerPlayer; //exclusive

        for (CardPlayer player : game.getPlayers().values()) {
            List<Card> cardsToDeal = cards.subList(fromIndex, toIndex);
            cardsToDeal.forEach(c -> c.setDealt(true));
            player.setHand(new ArrayList<>());
            player.getHand().addAll(cardsToDeal);
            fromIndex += numberOfCardsPerPlayer;
            toIndex += numberOfCardsPerPlayer;
        }
    }

    /**
     * This method creates an ordered map of players and their total (calculated based on face value of cards
     * in hand). The players are listed in descending order of their total.
     *
     * @param game
     * @return player to total map ordered by total descending
     */
    public Map<String, Integer> getResult(Game game) {
        return game.hasAnyPlayers() ? game.getPlayers().entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().calculateTotal(), Comparator.reverseOrder()))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().calculateTotal(),
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        )
                ) : new HashMap<>();
    }

    public Map<String, Long> getRemainingCardCountPerSuit(Game game) {
        return game.hasAnyDeck() ? game.getShoe().values()
                .stream()
                .flatMap(d -> d.getCards().stream())
                .filter(c -> !c.isDealt())
                .sorted(Comparator.comparing(Card::getSuit))
                .collect(Collectors.groupingBy(c -> String.format("[%s]",c.getSuit().getValue()), LinkedHashMap::new, Collectors.counting())) : new HashMap<>();
    }

    public Map<String, Long> getRemainingCardCountPerSuitAndRank(Game game) {
        return game.hasAnyDeck() ? game.getShoe().values()
                .stream()
                .flatMap(d -> d.getCards().stream())
                .filter(c -> !c.isDealt())
                .sorted(Comparator.comparing(Card::getSuit).thenComparing(c -> c.getRank().getValue(), Comparator.reverseOrder()))
                .collect(Collectors.groupingBy(c -> String.format("[%s|%s]", c.getRank().getDescription(), c.getSuit().getValue()), LinkedHashMap::new, Collectors.counting())) : new HashMap<>();
    }
}
