package pl.com.b2bnetwork.football.service.restService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.domain.modelToEntityMapper.LeagueModelToLeagueMapper;
import pl.com.b2bnetwork.football.exception.NotFoundException;
import pl.com.b2bnetwork.football.model.LeagueModel;
import pl.com.b2bnetwork.football.model.LeagueSuccess;
import pl.com.b2bnetwork.football.repository.CountryRepository;
import pl.com.b2bnetwork.football.repository.LeagueRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueServiceApiImpl {

    @Value("${URL_LEAGUE}")
    private String urlLeague;
    @Value("${API_KEY}")
    private String apiKey;

    private static final String HEADER_NAME = "user-agent";
    private static final String HEADER_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/"
            + "54.0.2840.99 Safari/537.36";

    private final RestTemplate restTemplate;

    private final CountryRepository countryRepository;

    private final LeagueRepository leagueRepository;

    public LeagueSuccess getLeagues() {
        final LeagueSuccess result;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME, HEADER_VALUE);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<LeagueSuccess> exchange = restTemplate.exchange(urlBuilder(), HttpMethod.GET, httpEntity, LeagueSuccess.class);
        result = exchange.getBody();
        return result;
    }

    public List<LeagueModel> allLeagues() {
        List<LeagueModel> result;
        result = getLeagues().getResult();
        result.sort((o1, o2) -> Integer.parseInt(o1.getLeagueKey()) > Integer.parseInt(o2.getLeagueKey()) ? 1 : -1);
        return result;
    }

    public void saveAllLeagues() {
        LeagueModelToLeagueMapper leagueModelToLeagueMapper = new LeagueModelToLeagueMapper();
        List<LeagueModel> leagueModels = allLeagues();
        for (LeagueModel leagueModel : leagueModels) {

            //TODO validacja czy istnieje country
            Country country = countryRepository.findById(Long.parseLong(leagueModel.getCountryKey())).get();
            leagueRepository.save(leagueModelToLeagueMapper.mapper(leagueModel, country));
        }
    }


    public LeagueModel getLeaguByName(final String leagueName, final LeagueModel leagueModel) {
        if (!leagueModel.getLeagueName().equals(leagueName)) {
            throw new NotFoundException("");
        } else {
            return leagueModel;
        }
    }

    public String urlBuilder() {
        StringBuilder url = new StringBuilder();
        url.append(urlLeague);
        url.append(apiKey);
        return url.toString();
    }
}
