package com.homework.game.service;

import com.homework.game.card.model.Deck;
import com.homework.game.exception.DeckAlreadyExistsException;
import com.homework.game.exception.NotFoundException;
import com.homework.game.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class GameService {

    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>(); //TODO: persist to DB?

    public Game createGame() {
        Game game = new Game(UUID.randomUUID().toString(), new HashMap<>(), new HashMap<>());
        games.put(game.getId(), game);
        return game;
    }

    public Game getGame(String gameId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException();
        }
        return games.get(gameId);
    }

    public void deleteGame(String gameId) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException();
        }
        games.remove(gameId);
    }

    public Game addDeckToGame(String gameId, Deck deck) {
        if(!games.containsKey(gameId)){
            throw new NotFoundException();
        }
        Game game = games.get(gameId);
        if(game.getShoe().containsKey(deck.getId())) {
            throw new DeckAlreadyExistsException();
        }
        game.getShoe().put(deck.getId(), deck);
        return game;
    }
}
