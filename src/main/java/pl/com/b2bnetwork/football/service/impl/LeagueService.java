package pl.com.b2bnetwork.football.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.com.b2bnetwork.football.domain.League;
import pl.com.b2bnetwork.football.dto.LeagueDto;

import java.util.List;

public interface LeagueService {


    List<League> findAll();

    LeagueDto findOneById(Long leagueId);

    void delete(Long id);

    void save(LeagueDto leagueDto);

    Page<League> findPaginated(Pageable pageable);
}
