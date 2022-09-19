package com.homework.game.controller;

import com.homework.game.card.model.Deck;
import com.homework.game.enums.Grouping;
import com.homework.game.model.Game;
import com.homework.game.player.model.CardPlayer;
import com.homework.game.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class GameController {

    private final GameService service;

    /**
     * This method is used to create a new instance of a Game. This has
     * no decks or players. The latter need to be added to play the game.
     *
     * @return created instance of the game
     */
    @Operation(
            summary = "Create new game."
//            security = {
//                    @SecurityRequirement(name = "BasicAuthentication"),
//                    @SecurityRequirement(name = "Bearer")
//            }
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created new game.",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Game.class))})})
    @PostMapping(value = "/game", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED )
    public Game createGame() {
        return service.createGame();
    }

    /**
     * This method is used to delete a given game using the specified game id.
     *
     * @param id of the game to be deleted
     */
    @Operation(summary = "Delete a given game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted a game."),
            @ApiResponse(responseCode = "404", description = "Could not find game.")
    })
    @DeleteMapping(value = "/game/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void deleteGame(@PathVariable String id) {
        service.deleteGame(id);
    }

    /**
     * This method is used to add a given deck of cards to the game deck of a given game.
     *
     * @param gameId of the game to which the deck will be added
     * @param deck to be added to the game deck
     * @return updated game
     */
    @Operation(summary = "Add deck to a given game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added deck to a game."),
            @ApiResponse(responseCode = "404", description = "Could not find game."),
            @ApiResponse(responseCode = "409", description = "Deck already exists in game.")
    })
    @PatchMapping(value = "/game/{gameId}/deck", produces = "application/json", consumes = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game addDeckToGame(@PathVariable String gameId, @RequestBody Deck deck) {
        return service.addDeckToGame(gameId, deck);
    }

    /**
     * This method is used to add a player to a given game.
     *
     * @param gameId of the game to which player will be added
     * @param player to be added to the game
     * @return updated game
     */
    @Operation(summary = "Add player to a given game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added player to a game."),
            @ApiResponse(responseCode = "404", description = "Could not find game."),
            @ApiResponse(responseCode = "409", description = "Player already exists in game.")
    })
    @PatchMapping(value = "/game/{gameId}/player", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game addPlayerToGame(@PathVariable String gameId, @RequestBody CardPlayer player) {
        return service.addPlayerToGame(gameId, player);
    }

    /**
     * This method is used to remove a player from a given game.
     *
     * @param gameId of the game from which the player needs to be removed
     * @param playerId of the player that requires removal
     * @return updated game
     */
    @Operation(summary = "Remove player from a given game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed player from a game."),
            @ApiResponse(responseCode = "404", description = "Could not find game/player.")
    })
    @PatchMapping(value = "/game/{gameId}/player/{playerId}", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game removePlayerFromGame(@PathVariable String gameId, @PathVariable String playerId) {
        return service.removePlayer(gameId, playerId);
    }

    /**
     * This method is used to deal the cards from the game deck equally to all the players present
     * in the game. Some cards will remain and this operation ends the game once the cards are
     * allocated.
     *
     * @param gameId of the game in which to deal the cards
     * @return the updated game
     */
    @Operation(summary = "Deal the cards from game deck.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cards dealt."),
            @ApiResponse(responseCode = "404", description = "Could not find game.")
    })
    @PutMapping(value = "/game/{gameId}/deal", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game dealCards(@PathVariable String gameId) {
        return service.dealCards(gameId);
    }

    /**
     * This method is used to shuffle all cards of the game deck basically shifting them to another
     * location within the same deck.
     *
     * @param gameId of the game which needs its cards shuffled
     * @return updated game
     */
    @Operation(summary = "Shuffle the cards from game deck.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shuffled cards of a game."),
            @ApiResponse(responseCode = "404", description = "Could not find game.")
    })
    @PutMapping(value = "/game/{gameId}/shuffle", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Game shuffleCards(@PathVariable String gameId) {
        return service.shuffleCards(gameId);
    }

    /**
     * This method is used to get the hand (cards held) of a given player from a given game.
     *
     * @param gameId of the game which contains the player
     * @param playerId of the player for whom the cards held are required
     * @return cards held by the specified player
     */
    @Operation(summary = "Get player's hand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved player's hand."),
            @ApiResponse(responseCode = "404", description = "Could not find game/player.")
    })
    @GetMapping(value = "/game/{gameId}/player/{playerId}/cards", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public List<String> getPlayerCards(@PathVariable String gameId, @PathVariable String playerId) {
        return service.getPlayerCards(gameId, playerId);
    }

    /**
     * This method is used to get the result of the game with the player having the strongest hand (total
     * face value of cards held) listed first.
     *
     * @param gameId of the game for which result is required
     * @return result of the game (user id and total)
     */
    @Operation(summary = "Get game's result.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved game's result."),
            @ApiResponse(responseCode = "404", description = "Could not find game.")
    })
    @GetMapping(value = "/game/{gameId}/result", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Map<String, Integer> getGameResult(@PathVariable String gameId) {
        return service.getResult(gameId);
    }

    /**
     * This method returns the cards remaining after cards are dealt equally. This count can be either per suit or
     * per suit and rank.
     *
     * @param gameId of the game for which we need the count
     * @param grouping can be either by suit or suitea and rank
     * @return count
     */
    @Operation(summary = "Get remaining card count (per suit or suit and rank).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved count."),
            @ApiResponse(responseCode = "404", description = "Could not find game.")
    })
    @GetMapping(value = "/game/{gameId}/deck/remaining-cards", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Map<String, Long> getRemainingGameCards(@PathVariable String gameId, @RequestParam Grouping grouping) {
        return service.getRemainingCardsCount(gameId, grouping);
    }
}
