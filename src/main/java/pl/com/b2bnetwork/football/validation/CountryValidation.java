package pl.com.b2bnetwork.football.validation;

import pl.com.b2bnetwork.football.exception.NotLetterField;
import pl.com.b2bnetwork.football.exception.NotValueInField;

public class CountryValidation {

    public static void countryNameValidation(final String countryName) {

        char space = ' ';

        if (countryName.isEmpty()) {
            throw new NotValueInField("Field name can not be empty");
        } else {
            for (int i = 0; i < countryName.length(); i++) {
                if (Character.isDigit(countryName.charAt(i)) && space != countryName.charAt(i)) {
                    throw new NotLetterField("Field name must consist of only letters");
                }
            }
        }
    }
}
