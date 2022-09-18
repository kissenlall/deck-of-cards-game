package com.homework.game.player.service;

import com.homework.game.player.model.CardPlayer;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    //TODO: persist player?

    public CardPlayer createCardPlayer() {
        return new CardPlayer();
    }
}
