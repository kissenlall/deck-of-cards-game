package com.homework.game.model;

import com.homework.game.card.model.Deck;
import com.homework.game.player.model.CardPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game extends EntityWithUUID {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.EAGER)
    private Map<String, Deck> shoe = new HashMap<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.EAGER)
    private Map<String, CardPlayer> players = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;

    //utility

    public boolean hasAnyPlayers() {
        return players != null && players.size() > 0;
    }

    public boolean hasAnyDeck() {
        return shoe != null && shoe.size() > 0;
    }

    public boolean hasPlayer(String playerId) {
        return players != null && players.containsKey(playerId);
    }

    public boolean hasDeck(String deckId) {
        return shoe != null && shoe.containsKey(deckId);
    }
}
