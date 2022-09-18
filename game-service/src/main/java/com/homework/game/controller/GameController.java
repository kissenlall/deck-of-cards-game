package com.homework.game.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.model.Game;
import com.homework.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "/game/{gameId}/player/{playerId}", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game addRemoveGamePlayer(@PathVariable String gameId, @PathVariable String playerId, @RequestParam String action) {
        return null;
    }

    @PatchMapping(value = "/game/{gameId}/deal", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game dealCards(@PathVariable String gameId) {
        return null;
    }

    @PatchMapping(value = "/game/{gameId}/shuffle", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game shuffleCards(@PathVariable String gameId) {
        return null;
    }

    @GetMapping(value = "/game/{gameId}/player/{playerId}/cards", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game getPlayerCards(@PathVariable String gameId, @PathVariable String playerId) {
        return null;
    }

    @GetMapping(value = "/game/{gameId}/result", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game getGameResult(@PathVariable String gameId) {
        return null;
    }

    @GetMapping(value = "/game/{gameId}/deck/cards", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game getGameCards(@PathVariable String gameId, @RequestParam String group) {
        return null;
    }

}
