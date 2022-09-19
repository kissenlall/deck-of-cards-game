package com.homework.game.player.controller;

import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    private final PlayerService service;

    @Operation(summary = "Create new card player.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created new card player.")
    })
    @PostMapping(value = "/player", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public CardPlayer createCardPlayer() {
        return service.createCardPlayer();
    }
}
