package com.homework.game.service;

import com.homework.game.card.dto.DeckDto;
import com.homework.game.dto.GameDto;
import com.homework.game.enums.Grouping;
import com.homework.game.exception.AlreadyExistsException;
import com.homework.game.exception.InvalidGameGameStateException;
import com.homework.game.exception.NotFoundException;
import com.homework.game.model.Game;
import com.homework.game.model.Status;
import com.homework.game.player.dto.CardPlayerDto;
import com.homework.game.repository.GameRepository;
import com.homework.game.utility.DtoToCardPlayerMapper;
import com.homework.game.utility.DtoToDeckMapper;
import com.homework.game.utility.GameToDtoMapper;
import com.homework.game.utility.GameUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository repository;
    private final GameToDtoMapper gameToDtoMapper;
    private final DtoToDeckMapper dtoToDeckMapper;
    private final DtoToCardPlayerMapper dtoToCardPlayerMapper;
    private final GameUtil gameUtil;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GameDto createGame() {
        return gameToDtoMapper.convert(repository.save(new Game()));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Game findGame(String gameId) {
        UUID uuid = UUID.fromString(gameId);
        return repository.findById(uuid).orElseThrow(() -> new NotFoundException(String.format("game does not exist : %s", gameId)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteGame(String gameId) {
        repository.delete(findGame(gameId));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GameDto addDeckToGame(String gameId, DeckDto deck) {
        Game game = findGame(gameId);
        if (game.hasDeck(deck.getId())) {
            throw new AlreadyExistsException(String.format("deck [%s] already exists in game [%s]", deck.getId(), gameId));
        }
        game.getShoe().put(deck.getId(), dtoToDeckMapper.convert(deck));
        return gameToDtoMapper.convert(game);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GameDto addPlayerToGame(String gameId, CardPlayerDto player) {
        Game game = findGame(gameId);
        if (game.hasPlayer(player.getId())) {
            throw new AlreadyExistsException(String.format("player [%s] already exists in game [%s]", player.getId(), gameId));
        }
        game.getPlayers().put(player.getId(), dtoToCardPlayerMapper.convert(player));
        return gameToDtoMapper.convert(game);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GameDto removePlayer(String gameId, String playerId) {
        Game game = findGame(gameId);
        if (!game.hasPlayer(playerId)) {
            throw new NotFoundException(String.format("could not find player [%s] in game [%s]", playerId, gameId));
        }
        game.getPlayers().remove(playerId);
        return gameToDtoMapper.convert(game);
    }

    //TODO: async and notify?

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GameDto dealCards(String gameId) {
        Game game = findGame(gameId);
        if (game.getStatus() == Status.ENDED) {
            throw new InvalidGameGameStateException(String.format("game has already ended [%s]", gameId));
        }
        if (game.getStatus() == Status.IN_PROGRESS) {
            throw new InvalidGameGameStateException(String.format("game is already in progress [%s]", gameId));
        }
        if (!game.hasAnyDeck()) {
            throw new InvalidGameGameStateException(String.format("at least one deck is required for game [%s]", gameId));
        }
        if (!game.hasAnyPlayers()) {
            throw new InvalidGameGameStateException(String.format("at least one player is required for game [%s]", gameId));
        }
        game.setStatus(Status.IN_PROGRESS);
        gameUtil.dealCards(game);
        game.setStatus(Status.ENDED);
        return gameToDtoMapper.convert(game);
    }

    //TODO: async and notify?

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GameDto shuffleCards(String gameId) {
        Game game = findGame(gameId);
        gameUtil.shuffleCards(game);
        return gameToDtoMapper.convert(game);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<String> getPlayerCards(String gameId, String playerId) {
        Game game = findGame(gameId);
        if (!game.hasPlayer(playerId)) {
            throw new NotFoundException(String.format("could not find player [%s] in game [%s]", playerId, gameId));
        }
        return game.getPlayers().get(playerId).getHand() != null ? game.getPlayers().get(playerId).getHand()
                .stream()
                .map(h -> String.format("%s%s", h.getRank().getDescription(), h.getSuit().getValue()))
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Integer> getResult(String gameId) {
        Game game = findGame(gameId);
        return gameUtil.getResult(game);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Long> getRemainingCardsCount(String gameId, Grouping grouping) {
        Game game = findGame(gameId);
        if (grouping == Grouping.SUIT_ONLY) {
            return gameUtil.getRemainingCardCountPerSuit(game);
        } else {
            return gameUtil.getRemainingCardCountPerSuitAndRank(game);
        }
    }
}
