package com.homework.game.card.model.playingcard;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.homework.game.card.model.AbstractCard;
import com.homework.game.card.model.serializer.PlayingCardSerializer;
import lombok.Value;

@Value
@JsonSerialize(using = PlayingCardSerializer.class)
public class Card extends AbstractCard {
    Rank rank;
    Suit suit;
}
