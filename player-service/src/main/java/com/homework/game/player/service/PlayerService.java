package com.homework.game.player.service;

import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.model.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    public Player createPlayer() {
        return new CardPlayer();
    }
}
