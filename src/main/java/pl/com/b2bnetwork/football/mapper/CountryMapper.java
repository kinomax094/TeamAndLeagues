package pl.com.b2bnetwork.football.mapper;

import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.dto.CountryDto;

import java.util.List;
import java.util.stream.Collectors;

public class CountryMapper {

    public CountryDto mapperCountryToDto(final Country country) {
        LeagueMapper leagueMapper = new LeagueMapper();
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setName(country.getName());
        countryDto.setLeagues(country.getLeagues()
                .stream()
                .map(n -> n.getName())
                .collect(Collectors.toList()));
        return countryDto;
    }

    public Country mapperDtoToCountry(final CountryDto countryDto) {
        LeagueMapper leagueMapper = new LeagueMapper();
        Country country = new Country();
        if (countryDto.getId() != null) {
            country.setId(countryDto.getId());
        }
        country.setName(countryDto.getName());
        return country;
    }

    public List<CountryDto> mapperCountryToDto(final List<Country> list) {
        List<CountryDto> collect = list.stream()
                .map(s -> mapperCountryToDto(s))
                .collect(Collectors.toList());
        return collect;
    }

    public List<Country> mapperDtoToCountry(final List<CountryDto> list) {
        List<Country> collect = list.stream()
                .map(s -> mapperDtoToCountry(s))
                .collect(Collectors.toList());
        return collect;
    }
}
