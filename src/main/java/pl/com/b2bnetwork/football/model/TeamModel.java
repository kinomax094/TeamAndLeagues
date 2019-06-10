package pl.com.b2bnetwork.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamModel {

    @JsonProperty("team_key")
    private String teamKey;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("team_logo")
    private String teamLogo;
    @JsonProperty("players")
    private List<PlayerModel> players;
}
