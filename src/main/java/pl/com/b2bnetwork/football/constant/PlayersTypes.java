package pl.com.b2bnetwork.football.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import pl.com.b2bnetwork.football.exception.NoSuchTypePlayerException;

public enum PlayersTypes {

    GOALKEEPERS("Goalkeepers"),
    DEFENDERS("Defenders"),
    MIDFIELDERS("Midfielders"),
    FORWARDS("Forwards");

    private String value;

    PlayersTypes(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


    @JsonCreator
    public static PlayersTypes values(final String playersType) {
        PlayersTypes result = null;
        for (PlayersTypes value : values()) {
            if (value.getValue().equals(playersType)) {
                result = value;
            }
        }
        if (result == null) {
            throw new NoSuchTypePlayerException("The type of player position don't exist");

        }
        return result;
    }

}
