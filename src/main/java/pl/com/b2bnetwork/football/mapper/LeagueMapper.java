package pl.com.b2bnetwork.football.mapper;

import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.domain.League;
import pl.com.b2bnetwork.football.dto.LeagueDto;

public class LeagueMapper {

    public LeagueDto mapperLeagueToDto(final League league) {
        LeagueDto leagueDto = new LeagueDto();
        CountryMapper countryMapper = new CountryMapper();
        leagueDto.setCountryId(league.getCountry().getId());
        leagueDto.setCountryName(league.getCountry().getName());
        leagueDto.setName(league.getName());
        leagueDto.setId(league.getId());
        return leagueDto;
    }

    public League mapperDtoToLeague(final LeagueDto leagueDto, final Country country) {
        League league = new League();
        league.setCountry(country);
        league.setName(leagueDto.getName());
        if (leagueDto.getId() != null) {
            league.setId(leagueDto.getId());
        }
        return league;
    }
}
