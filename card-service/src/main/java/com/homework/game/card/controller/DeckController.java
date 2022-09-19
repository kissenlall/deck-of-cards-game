package com.homework.game.card.controller;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.card.model.Deck;
import com.homework.game.card.service.DeckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class DeckController {

    private final DeckService service;

    /**
     * This method is used to create a new deck of playing cards.
     *
     * @return created deck of playing cards
     */
    @Operation(summary = "Create new deck of playing cards.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created new deck of cards.")
    })
    @PostMapping(value = "/deck/playing-cards", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED )
    public DeckDto createDeckOfPlayingCards() {
        return service.createDeckOfPlayingCards();
    }
}
