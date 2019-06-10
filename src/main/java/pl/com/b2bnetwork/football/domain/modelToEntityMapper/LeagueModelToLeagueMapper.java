package pl.com.b2bnetwork.football.domain.modelToEntityMapper;

import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.domain.League;
import pl.com.b2bnetwork.football.model.LeagueModel;

public class LeagueModelToLeagueMapper {

    public League mapper(final LeagueModel leagueModel, final Country country) {
        League league = new League();
        league.setLeagueKey(Long.parseLong(leagueModel.getLeagueKey()));
        league.setName(leagueModel.getLeagueName());
        league.setCountry(country);
        return league;
    }

}
