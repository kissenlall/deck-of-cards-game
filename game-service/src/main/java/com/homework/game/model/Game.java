package com.homework.game.model;

import com.homework.game.card.model.Card;
import com.homework.game.card.model.Deck;
import com.homework.game.exception.InvalidGameGameStateException;
import com.homework.game.player.model.CardPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class Game {

    @Setter
    private Status status = Status.NOT_STARTED;

    private final String id;
    private final Map<String, Deck> shoe;
    private final Map<String, CardPlayer> players;

    public Map<String, Long> getUndealtCardCountPerSuit() {
        return shoe.values()
                .stream()
                .flatMap(d -> d.getCards().stream())
                .filter(c -> !c.isDealt())
                .sorted(Comparator.comparing(Card::getSuit))
                .collect(Collectors.groupingBy(c -> c.getSuit().getValue(), LinkedHashMap::new, Collectors.counting()));
    }

    public Map<String, Long> getUndealtCardCountPerSuitAndRank() {
        return shoe.values()
                .stream()
                .flatMap(d -> d.getCards().stream())
                .filter(c -> !c.isDealt())
                .sorted(Comparator.comparing(Card::getSuit).thenComparing(c -> c.getRank().getValue(), Comparator.reverseOrder()))
                .collect(Collectors.groupingBy(c -> String.format("%s%s",c.getRank().getDescription(), c.getSuit().getValue()), LinkedHashMap::new, Collectors.counting()));
    }

    public Map<String, Integer> getResult() {
        return players.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().calculateTotal(), Comparator.reverseOrder()))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().calculateTotal(),
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        )
                );
    }

    public void dealCards() {

        if (status == Status.ENDED) {
            throw new InvalidGameGameStateException(String.format("game has already ended [%s]", id));
        }
        if (status == Status.IN_PROGRESS) {
            throw new InvalidGameGameStateException(String.format("game is already in progress [%s]", id));
        }
        if (shoe.size() < 1) {
            throw new InvalidGameGameStateException(String.format("at least one deck is required for game [%s]", id));
        }
        if (players.size() < 1) {
            throw new InvalidGameGameStateException(String.format("at least one player is required for game [%s]", id));
        }

        status = Status.IN_PROGRESS;

        shuffle();

        List<Card> cards = shoe.values()
                .stream()
                .flatMap(s -> s.getCards().stream())
                .collect(Collectors.toList());

        int size = cards.size() / players.values().size(); // how many cards per player?
        int index = 0;

        for (CardPlayer player : players.values()) {
            List<Card> cardsToDeal = cards.subList(index, size);
            cardsToDeal.forEach(c -> c.setDealt(true));
            player.getHand().addAll(cardsToDeal);
            index += size;
            size += size;
        }

        status = Status.ENDED;
    }

    public void shuffle() {
        for (Deck deck : shoe.values()) {
            shuffle(deck); // swap current position to somewhere else in same deck
        }
    }

    private void shuffle(Deck deck) {
        Random r = new Random();
        for (int i = deck.getCards().size() - 1; i > 0; i--) {
            int j = r.nextInt(i);
            Collections.swap(deck.getCards(), i, j);
        }
    }
}
