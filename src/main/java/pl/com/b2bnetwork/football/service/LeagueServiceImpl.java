package pl.com.b2bnetwork.football.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.domain.League;
import pl.com.b2bnetwork.football.dto.LeagueDto;
import pl.com.b2bnetwork.football.exception.NotFoundException;
import pl.com.b2bnetwork.football.mapper.LeagueMapper;
import pl.com.b2bnetwork.football.repository.CountryRepository;
import pl.com.b2bnetwork.football.repository.LeagueRepository;
import pl.com.b2bnetwork.football.service.impl.LeagueService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository repository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<League> findAll() {
        return repository.findAll();
    }

    @Override
    public LeagueDto findOneById(final Long leagueId) {

        LeagueMapper leagueMapper = new LeagueMapper();
        Optional<League> optionalLeague = repository.findById(leagueId);
        League result;
        if (!optionalLeague.isPresent()) {
            throw new NotFoundException("League not found");
        } else {
            result = optionalLeague.get();
        }

        return leagueMapper.mapperLeagueToDto(result);
    }

    @Override
    public void delete(final Long leagueId) {
        repository.deleteById(leagueId);
    }

    @Override
    public void save(final LeagueDto leagueDto) {

        LeagueMapper leagueMapper = new LeagueMapper();
        Country country = countryRepository.findById(leagueDto.getCountryId()).get();

        repository.save(leagueMapper.mapperDtoToLeague(leagueDto, country));
    }

    @Override
    public Page<League> findPaginated(final Pageable pageable) {
        List<League> leagueList = findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<League> list;

        if (leagueList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, leagueList.size());
            list = leagueList.subList(startItem, toIndex);
        }

        Page<League> leaguePage
                = new PageImpl<League>(list, PageRequest.of(currentPage, pageSize), leagueList.size());

        return leaguePage;
    }


}
