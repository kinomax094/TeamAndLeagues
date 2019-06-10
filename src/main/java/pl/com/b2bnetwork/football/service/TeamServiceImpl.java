package pl.com.b2bnetwork.football.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.b2bnetwork.football.domain.Team;
import pl.com.b2bnetwork.football.dto.TeamDto;
import pl.com.b2bnetwork.football.exception.NotFoundException;
import pl.com.b2bnetwork.football.mapper.TeamMapper;
import pl.com.b2bnetwork.football.repository.TeamRepository;
import pl.com.b2bnetwork.football.service.impl.TeamService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository repository;

    @Override
    public List<TeamDto> showTeams() {
        TeamMapper teamMapper = new TeamMapper();
        List<TeamDto> result = teamMapper.mapperTeamToDto(repository.findAll());
        return result;
    }

    @Override
    public TeamDto findById(final Long id) {
        TeamMapper mapper = new TeamMapper();
        TeamDto result;
        Optional<Team> team = repository.findById(id);
        if (!team.isPresent()) {
            throw new NotFoundException("Nie znaleziono Drużyny");

        } else {
            result = mapper.mapperTeamToDto(team.get());
        }
        return result;
    }

    @Override
    public void delete(final Long id) {
        Optional<Team> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException("Nie znaleziono Drużyny");
        } else {
            repository.delete(optional.get());
        }
    }

    @Override
    public void save(final TeamDto teamDto) {
        TeamMapper mapper = new TeamMapper();
        Team team = mapper.mapperDtoToTeam(teamDto);
        repository.save(team);
    }

    @Override
    public String getNameById(final Long id) {
        Optional<Team> optional = repository.findById(id);
        String result = "";
        if (!optional.isPresent()) {

            throw new NotFoundException("Nie znaleziono Drużyny");

        } else {
            result = optional.get().getName();
        }
        return result;
    }

    @Override
    public Page<TeamDto> findPaginated(final Pageable pageable) {
        List<TeamDto> teamList = showTeams();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TeamDto> list;

        if (teamList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, showTeams().size());
            list = teamList.subList(startItem, toIndex);
        }

        Page<TeamDto> teamPage
                = new PageImpl<TeamDto>(list, PageRequest.of(currentPage, pageSize), teamList.size());

        return teamPage;
    }

}
