package pl.com.b2bnetwork.football.repository;

import pl.com.b2bnetwork.football.domain.Player;
import pl.com.b2bnetwork.football.dto.QueryArgsForFindPlayer;

import java.util.List;
import java.util.Optional;

public interface QueryRepositoryPlayer {

    Optional<List<Player>> findPlayers(QueryArgsForFindPlayer queryArgsForFindPlayer);
}
