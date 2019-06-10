package pl.com.b2bnetwork.football.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.com.b2bnetwork.football.dto.PlayerDto;
import pl.com.b2bnetwork.football.dto.QueryArgsForFindPlayer;

import java.util.List;

public interface PlayerService {

    List<PlayerDto> getAllPlayers();

    PlayerDto findById(Long id);

    void delete(Long id);

    void save(PlayerDto playerDto);

    List<PlayerDto> findPlayers(QueryArgsForFindPlayer queryArgsForFindPlayer);

    Page<PlayerDto> findPaginated(Pageable pageable, QueryArgsForFindPlayer queryArgsForFindPlayer);
}
