package com.homework.game.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.enums.Grouping;
import com.homework.game.model.Game;
import com.homework.game.player.model.CardPlayer;
import com.homework.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService service;

    @GetMapping(value = "/game", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED )
    public Game createGame() {
        return service.createGame();
    }

    @GetMapping(value = "/game/{id}", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game getGame(@PathVariable String id) {
        return service.getGame(id);
    }

    @DeleteMapping(value = "/game/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void deleteGame(@PathVariable String id) {
        service.deleteGame(id);
    }

    @PatchMapping(value = "/game/{gameId}/deck", produces = "application/json", consumes = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game addDeckToGame(@PathVariable String gameId, @RequestBody Deck deck) {
        return service.addDeckToGame(gameId, deck);
    }

    @PatchMapping(value = "/game/{gameId}/player", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game addGamePlayer(@PathVariable String gameId, @RequestBody CardPlayer player) {
        return service.addPlayerToGame(gameId, player);
    }

    @PatchMapping(value = "/game/{gameId}/player/{playerId}", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game removeGamePlayer(@PathVariable String gameId, @PathVariable String playerId) {
        return service.removePlayer(gameId, playerId);
    }

    @PatchMapping(value = "/game/{gameId}/deal", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game dealCards(@PathVariable String gameId) {
        return service.deal(gameId);
    }

    @PatchMapping(value = "/game/{gameId}/shuffle", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game shuffleCards(@PathVariable String gameId) {
        return service.shuffle(gameId);
    }

    @GetMapping(value = "/game/{gameId}/player/{playerId}/cards", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public List<String> getPlayerCards(@PathVariable String gameId, @PathVariable String playerId) {
        return service.getPlayerCards(gameId, playerId);
    }

    @GetMapping(value = "/game/{gameId}/result", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Map<String, Integer> getGameResult(@PathVariable String gameId) {
        return service.getResult(gameId);
    }

    @GetMapping(value = "/game/{gameId}/deck/remaining-cards", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Map<String, Long> getRemainingGameCards(@PathVariable String gameId, @RequestParam Grouping grouping) {
        return service.getRemainingCardsCount(gameId, grouping);
    }
}
