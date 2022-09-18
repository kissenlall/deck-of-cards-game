package com.homework.game.model;

import com.homework.game.card.model.Deck;
import com.homework.game.player.model.CardPlayer;
import lombok.Value;

import java.util.Map;

@Value
public class Game {
    String id;
    Map<String, Deck> shoe;
    Map<String, CardPlayer> players;
}
