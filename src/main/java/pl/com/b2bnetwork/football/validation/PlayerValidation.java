package pl.com.b2bnetwork.football.validation;

import pl.com.b2bnetwork.football.constant.PlayersTypes;
import pl.com.b2bnetwork.football.dto.PlayerDto;
import pl.com.b2bnetwork.football.dto.QueryArgsForFindPlayer;
import pl.com.b2bnetwork.football.exception.NotDigitalField;
import pl.com.b2bnetwork.football.exception.NotLetterField;
import pl.com.b2bnetwork.football.exception.NotValueInField;

public class PlayerValidation {


    public static void objectForFindPlayerValidation(final QueryArgsForFindPlayer querArgs) {
        char space = ' ';
        String name = querArgs.getName();
        if (!name.isEmpty()) {
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetter(name.charAt(i)) && space != name.charAt(i)) {
                    throw new NotLetterField("Field country must consist of only letters");
                }
            }
        }
        String country = querArgs.getCountry();
        if (!country.isEmpty()) {
            for (int i = 0; i < country.length(); i++) {
                if (!Character.isLetter(country.charAt(i)) && space != name.charAt(i)) {
                    throw new NotLetterField("Field country must consist of only letters");
                }
            }
        }
    }

    public static void objectPlayerDtoValidation(final PlayerDto playerDto) {
        char space = ' ';
        String name = playerDto.getName();
        if (name.isEmpty()) {
            throw new NotValueInField("Field name can not be empty");
        } else {
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetter(name.charAt(i)) && space != name.charAt(i)) {
                    throw new NotLetterField("Field name must consist of only letters");
                }
            }
        }
        String number = playerDto.getNumber();
        if (!number.isEmpty()) {
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    throw new NotDigitalField("Field Number must consist of only digital");
                }
            }
        }
        String country = playerDto.getPlayerCountry();
        if (country.isEmpty()) {
            throw new NotValueInField("Field country can not be empty");
        } else {
            for (int i = 0; i < country.length(); i++) {
                if (!Character.isLetter(country.charAt(i)) && space != country.charAt(i)) {
                    throw new NotLetterField("Field country must consist of only letters");
                }
            }
        }

        String type = playerDto.getType();
        if (!type.isEmpty()) {
            PlayersTypes.values(type);
        }
        String age = playerDto.getAge();
        if (!age.isEmpty()) {
            for (int i = 0; i < age.length(); i++) {
                if (!Character.isDigit(age.charAt(i))) {
                    throw new NotDigitalField("Field Age must consist of only digital");
                }
            }
        }
        String matchPlayed = playerDto.getMatchPlayed();
        if (!matchPlayed.isEmpty()) {
            for (int i = 0; i < matchPlayed.length(); i++) {
                if (!Character.isDigit(matchPlayed.charAt(i))) {
                    throw new NotDigitalField("Field Played match must consist of only digital");
                }
            }
        }
        String goals = playerDto.getGoals();
        if (!goals.isEmpty()) {
            for (int i = 0; i < goals.length(); i++) {
                if (!Character.isDigit(goals.charAt(i))) {
                    throw new NotDigitalField("Field Goals must consist of only digital");
                }
            }
        }
        String yellowCards = playerDto.getYellowCards();
        if (!yellowCards.isEmpty()) {
            for (int i = 0; i < yellowCards.length(); i++) {
                if (!Character.isDigit(yellowCards.charAt(i))) {
                    throw new NotDigitalField("Field Yellow Cards must consist of only digital");
                }
            }
            String redCards = playerDto.getRedCards();
            if (!redCards.isEmpty()) {
                for (int i = 0; i < redCards.length(); i++) {
                    if (!Character.isDigit(redCards.charAt(i))) {
                        throw new NotDigitalField("Field Red Cards must consist of only digital");
                    }
                }
            }
        }
    }

}