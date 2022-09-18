package com.homework.game.player.service;

import com.homework.game.player.model.CardPlayer;
import com.homework.game.player.model.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    //TODO: persist player?

    public Player createCardPlayer() {
        return new CardPlayer();
    }
}
