package pl.com.b2bnetwork.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueModel {

    @JsonProperty("league_key")
    private String leagueKey;
    @JsonProperty("league_name")
    private String leagueName;
    @JsonProperty("country_key")
    private String countryKey;
    @JsonProperty("country_name")
    private String countryName;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeagueModel that = (LeagueModel) o;
        return Objects.equals(leagueKey, that.leagueKey)
                && Objects.equals(leagueName, that.leagueName)
                && Objects.equals(countryKey, that.countryKey)
                && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueKey, leagueName, countryKey, countryName);
    }
}
