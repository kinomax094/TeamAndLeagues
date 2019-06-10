package pl.com.b2bnetwork.football.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.domain.League;
import pl.com.b2bnetwork.football.exception.NotFoundException;
import pl.com.b2bnetwork.football.repository.CountryRepository;
import pl.com.b2bnetwork.football.service.impl.CountryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;


    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Country findOneByName(final String countryName) {
        Optional<Country> optionalCountry = repository.findOneByName(countryName);
        Country result;

        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Country not found");
        } else {
            result = optionalCountry.get();
        }
        return result;
    }

    @Override
    public Country findOneById(final Long countryId) {

        Optional<Country> optionalCountry = repository.findById(countryId);
        Country result;

        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Country not found");
        } else {
            result = optionalCountry.get();
        }
        return result;
    }

    @Override
    public List<League> findAllLeaguesForGivenCountryId(final Long countryId) {
        return findOneById(countryId).getLeagues();
    }

    @Override
    public Page<Country> findPaginated(final Pageable pageable) {
        List<Country> listCountry = findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Country> list;

        if (listCountry.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listCountry.size());
            list = listCountry.subList(startItem, toIndex);
        }

        Page<Country> countryPage
                = new PageImpl<Country>(list, PageRequest.of(currentPage, pageSize), listCountry.size());

        return countryPage;
    }
}
