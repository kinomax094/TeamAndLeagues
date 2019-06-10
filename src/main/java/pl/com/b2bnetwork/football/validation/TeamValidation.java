package pl.com.b2bnetwork.football.validation;

import pl.com.b2bnetwork.football.dto.TeamDto;
import pl.com.b2bnetwork.football.exception.NotLetterField;
import pl.com.b2bnetwork.football.exception.NotValueInField;

public class TeamValidation {


    public static void objectTeamDtoValidation(final TeamDto teamDto) {
        char space = ' ';

        String name = teamDto.getName();
        if (name.isEmpty()) {
            throw new NotValueInField("Field name can not be empty");
        } else {
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetter(name.charAt(i)) && space != name.charAt(i)) {
                    throw new NotLetterField("Field name must consist of only letters");
                }
            }
        }

    }

}
