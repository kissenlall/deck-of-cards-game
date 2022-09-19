package com.homework.game.player.model;

import com.homework.game.card.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPlayer implements Serializable {

    private String id;
    private List<Card> hand = new ArrayList<>();

    //utility

    public int calculateTotal() {
        return hand != null ? hand.stream().mapToInt(c -> c.getRank().getValue()).sum() : 0;
    }
}
