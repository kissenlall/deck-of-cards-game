package com.homework.game.service;

import com.homework.game.card.model.Deck;
import com.homework.game.enums.Grouping;
import com.homework.game.exception.AlreadyExistsException;
import com.homework.game.exception.NotFoundException;
import com.homework.game.model.Game;
import com.homework.game.player.model.CardPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {

    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>(); //TODO: persist to DB?

    public Game createGame() {
        Game game = new Game(UUID.randomUUID().toString(), new HashMap<>(), new HashMap<>());
        games.put(game.getId(), game);
        return game;
    }

    public void deleteGame(String gameId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        games.remove(gameId);
    }

    public Game addDeckToGame(String gameId, Deck deck) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        Game game = games.get(gameId);
        if(game.getShoe().containsKey(deck.getId())) {
            throw new AlreadyExistsException(String.format("deck [%s] already exists in game [%s]", deck.getId(), gameId));
        }
        game.getShoe().put(deck.getId(), deck);
        return game;
    }

    public Game addPlayerToGame(String gameId, CardPlayer player) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        Game game = games.get(gameId);
        if(game.getPlayers().containsKey(player.getId())) {
            throw new AlreadyExistsException(String.format("player [%s] already exists in game [%s]", player.getId(), gameId));
        }
        game.getPlayers().put(player.getId(), player);
        return game;
    }

    public Game removePlayer(String gameId, String playerId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        Game game = games.get(gameId);
        if(!game.getPlayers().containsKey(playerId)) {
            throw new NotFoundException(String.format("could not find player [%s] in game [%s]", playerId, gameId));
        }
        game.getPlayers().remove(playerId);
        return game;
    }

    public Game dealCards(String gameId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        Game game = games.get(gameId);
        game.dealCards();
        return game;
    }

    public Game shuffleCards(String gameId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        Game game = games.get(gameId);
        game.shuffle();
        return game;
    }

    public List<String> getPlayerCards(String gameId, String playerId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        Game game = games.get(gameId);
        if(!game.getPlayers().containsKey(playerId)) {
            throw new NotFoundException(String.format("could not find player [%s] in game [%s]", playerId, gameId));
        }
        return game.getPlayers().get(playerId).getHand()
                .stream()
                .map(h -> String.format("%s%s", h.getRank().getDescription(), h.getSuit().getValue()))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getResult(String gameId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        return games.get(gameId).getResult();
    }

    public Map<String, Long> getRemainingCardsCount(String gameId, Grouping grouping) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException(String.format("could not find game [%s]", gameId));
        }
        return grouping == Grouping.SUIT_ONLY ? games.get(gameId).getUndealtCardCountPerSuit() : games.get(gameId).getUndealtCardCountPerSuitAndRank();
    }
}
