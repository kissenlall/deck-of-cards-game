package com.homework.game.utility;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.card.utility.DeckToDtoMapper;
import com.homework.game.dto.GameDto;
import com.homework.game.model.Game;
import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.player.utility.CardPlayerToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GameToDtoMapper implements Converter<Game, GameDto> {

    private final DeckToDtoMapper deckToDtoMapper;
    private final CardPlayerToDtoMapper cardPlayerToDtoMapper;

    @Override
    public GameDto convert(Game source) {

        Map<String, DeckDto> shoe = null;
        if(source.getShoe() != null && !source.getShoe().isEmpty()) {
            shoe = source.getShoe()
                    .entrySet()
                    .stream().collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> deckToDtoMapper.convert(e.getValue())
                            )
                    );
        }

        Map<String, CardPlayerDto> players = null;
        if(source.getPlayers() != null && !source.getPlayers().isEmpty()) {
            players = source.getPlayers()
                    .entrySet()
                    .stream().collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> cardPlayerToDtoMapper.convert(e.getValue())
                            )
                    );
        }

        return new GameDto(source.getId().toString(), shoe, players, source.getStatus().name());
    }
}
