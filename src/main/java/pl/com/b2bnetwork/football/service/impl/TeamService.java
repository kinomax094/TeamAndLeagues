package pl.com.b2bnetwork.football.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.com.b2bnetwork.football.dto.TeamDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> showTeams();

    TeamDto findById(Long x);

    void delete(Long id);

    void save(TeamDto playerDto);

    String getNameById(Long id);

    Page<TeamDto> findPaginated(Pageable pageable);
}
