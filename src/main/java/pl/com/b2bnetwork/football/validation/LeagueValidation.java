package pl.com.b2bnetwork.football.validation;

import pl.com.b2bnetwork.football.dto.LeagueDto;
import pl.com.b2bnetwork.football.exception.NotLetterField;
import pl.com.b2bnetwork.football.exception.NotValueInField;

public class LeagueValidation {

    public static void leagueDtoValidation(final LeagueDto leagueDto) {

        char space = ' ';
        String name = leagueDto.getName();

        if (name.isEmpty()) {
            throw new NotValueInField("Field name can not be empty");
        } else {
            for (int i = 0; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i)) && space != name.charAt(i)) {
                    throw new NotLetterField("Field name must consist of only letters");
                }
            }
        }
    }
}
