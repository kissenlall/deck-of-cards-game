package com.homework.game.utility;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.card.model.Card;
import com.homework.game.card.model.Deck;
import com.homework.game.card.model.Rank;
import com.homework.game.card.model.Suit;
import com.homework.game.card.utility.CardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DtoToDeckMapper implements Converter<DeckDto, Deck> {

    private final CardUtil cardUtil;

    @Override
    public Deck convert(DeckDto source) {
        List<Card> cards = null;
        if(source.getCards() != null && !source.getCards().isEmpty()) {
            cards = source.getCards()
                    .stream()
                    .map( c -> new Card(Rank.fromDescription(cardUtil.toRank(c)), Suit.fromValue(cardUtil.toSuit(c)), false))
                    .collect(Collectors.toList());
        }
        return new Deck(source.getId(), cards);
    }
}
