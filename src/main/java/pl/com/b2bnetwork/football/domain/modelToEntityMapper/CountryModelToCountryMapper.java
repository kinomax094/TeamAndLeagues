package pl.com.b2bnetwork.football.domain.modelToEntityMapper;

import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.model.CountryModel;

public class CountryModelToCountryMapper {

    public Country mapper(final CountryModel countryModel) {
        Country country = new Country();
        country.setCountryKey(Long.parseLong(countryModel.getCountryKey()));
        country.setName(countryModel.getCountryName());
        return country;
    }
}
