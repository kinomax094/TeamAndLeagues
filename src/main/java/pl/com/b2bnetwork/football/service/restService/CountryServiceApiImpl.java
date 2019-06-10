package pl.com.b2bnetwork.football.service.restService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.com.b2bnetwork.football.domain.modelToEntityMapper.CountryModelToCountryMapper;
import pl.com.b2bnetwork.football.model.CountryModel;
import pl.com.b2bnetwork.football.model.CountrySuccess;
import pl.com.b2bnetwork.football.repository.CountryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceApiImpl {

    private final CountryRepository countryRepository;

    @Value("${URL_COUNTRY}")
    private String urlCountry;
    @Value("${API_KEY}")
    private String apiKey;

    private static final String HEADER_NAME = "user-agent";
    private static final String HEADER_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/"
            + "54.0.2840.99 Safari/537.36";

    @Autowired
    private RestTemplate restTemplate;

    public CountrySuccess getAllCountries() {
        final CountrySuccess result;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME, HEADER_VALUE);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<CountrySuccess> exchange = restTemplate.exchange(urlBuilder(), HttpMethod.GET, httpEntity, CountrySuccess.class);
        result = exchange.getBody();
        return result;
    }

    public List<CountryModel> allCountries() {
        List<CountryModel> result = new ArrayList<>();
        result = getAllCountries().getResult();
        result.sort((o1, o2) -> Integer.parseInt(o1.getCountryKey()) > Integer.parseInt(o2.getCountryKey()) ? 1 : -1);
        return result;
    }

    public void saveAllCountries() {
        CountryModelToCountryMapper countryModelToCountryMapper = new CountryModelToCountryMapper();
        List<CountryModel> countryModels = allCountries();
        for (CountryModel countryModel : countryModels) {
            countryRepository.save(countryModelToCountryMapper.mapper(countryModel));
        }
    }


    public String urlBuilder() {
        StringBuilder url = new StringBuilder();
        url.append(urlCountry);
        url.append(apiKey);
        return url.toString();
    }
}
