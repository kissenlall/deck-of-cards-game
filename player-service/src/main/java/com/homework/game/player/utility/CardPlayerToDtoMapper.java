package com.homework.game.player.utility;

import com.homework.game.card.utility.CardUtil;
import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.player.model.CardPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CardPlayerToDtoMapper implements Converter<CardPlayer, CardPlayerDto> {

    private final CardUtil cardUtil;

    @Override
    public CardPlayerDto convert(CardPlayer source) {

        List<String> cards =null;

        if(source.getHand() != null && !source.getHand().isEmpty()) {
            cards = source.getHand()
                    .stream()
                    .map(cardUtil::toString)
                    .collect(Collectors.toList());
        }


        return new CardPlayerDto(source.getId(), cards);
    }
}
