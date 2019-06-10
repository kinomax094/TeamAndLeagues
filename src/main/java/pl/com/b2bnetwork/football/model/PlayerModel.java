package pl.com.b2bnetwork.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.com.b2bnetwork.football.constant.PlayersTypes;

@Getter
@Setter
@NoArgsConstructor
public class PlayerModel {

    @JsonProperty("player_key")
    private String playerKey;
    @JsonProperty("player_name")
    private String playerName;
    @JsonProperty("player_number")
    private String playerNumber;
    @JsonProperty("player_country")
    private String playerCountry;
    @JsonProperty("player_type")
    private PlayersTypes playerType;
    @JsonProperty("player_age")
    private String playerAge;
    @JsonProperty("player_match_played")
    private String playerMatchPlayed;
    @JsonProperty("player_goals")
    private String playerGoals;
    @JsonProperty("player_yellow_cards")
    private String playerYellowCards;
    @JsonProperty("player_red_cards")
    private String playerRedCards;
}
