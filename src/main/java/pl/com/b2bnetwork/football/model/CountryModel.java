package pl.com.b2bnetwork.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryModel {

    @JsonProperty("country_key")
    private String countryKey;
    @JsonProperty("country_name")
    private String countryName;
}
