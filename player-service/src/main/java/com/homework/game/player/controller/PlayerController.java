package com.homework.game.player.controller;

import com.homework.game.player.model.Player;
import com.homework.game.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlayerController {

    private final PlayerService service;

    @GetMapping(value = "/player", produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public Player createPlayer() {
        return service.createPlayer();
    }
}
