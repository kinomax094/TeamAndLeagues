package pl.com.b2bnetwork.football.domain.modelToEntityMapper;

import pl.com.b2bnetwork.football.domain.Team;
import pl.com.b2bnetwork.football.model.TeamModel;

public class TeamModelToTeamMapper {

    public Team mapper(final TeamModel teamModel) {
        Team team = new Team();
        team.setTeamKey(Long.parseLong(teamModel.getTeamKey()));
        team.setName(teamModel.getTeamName());
        team.setLogo(teamModel.getTeamLogo());
        return team;
    }
}
