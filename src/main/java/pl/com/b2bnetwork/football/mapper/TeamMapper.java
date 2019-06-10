package pl.com.b2bnetwork.football.mapper;

import pl.com.b2bnetwork.football.domain.Team;
import pl.com.b2bnetwork.football.dto.TeamDto;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public TeamDto mapperTeamToDto(final Team team) {
        PlayerMapper playerMapper = new PlayerMapper();
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setLogo(team.getLogo());
        if (team.getPlayers() != null) {
            teamDto.setPlayers(playerMapper.mapperPlayerToDto(team.getPlayers()));
        }

        return teamDto;
    }

    public Team mapperDtoToTeam(final TeamDto teamDto) {
        PlayerMapper playerMapper = new PlayerMapper();
        Team team = new Team();
        if (teamDto.getId() != null) {
            team.setId(teamDto.getId());
        }
        team.setName(teamDto.getName());
        team.setLogo(teamDto.getLogo());
        if (teamDto.getPlayers() != null) {
            team.setPlayers(playerMapper.mapperDtoToPlayer(teamDto.getPlayers()));
        }
        return team;
    }

    public List<TeamDto> mapperTeamToDto(final List<Team> list) {
        List<TeamDto> collect = list.stream()
                .map(s -> mapperTeamToDto(s))
                .collect(Collectors.toList());
        return collect;
    }

    public List<Team> mapperDtoToTeam(final List<TeamDto> list) {
        List<Team> collect = list.stream()
                .map(s -> mapperDtoToTeam(s))
                .collect(Collectors.toList());
        return collect;
    }
}
