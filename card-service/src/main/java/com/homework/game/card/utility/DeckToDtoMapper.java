package com.homework.game.card.utility;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.card.model.Deck;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DeckToDtoMapper implements Converter<Deck, DeckDto> {

    private final CardUtil cardUtil;

    @Override
    public DeckDto convert(Deck source) {

        List<String> cards = null;

        if(source.getCards() != null && !source.getCards().isEmpty()) {
            cards = source.getCards()
                    .stream()
                    .map(cardUtil::toString)
                    .collect(Collectors.toList());
        }

        return new DeckDto(source.getId(), cards);
    }
}
