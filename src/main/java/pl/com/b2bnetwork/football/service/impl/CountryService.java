package pl.com.b2bnetwork.football.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.domain.League;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findOneByName(String countryName);

    Country findOneById(Long countryId);

    List<League> findAllLeaguesForGivenCountryId(Long countryId);

    Page<Country> findPaginated(Pageable pageable);
}
