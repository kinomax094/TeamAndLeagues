package pl.com.b2bnetwork.football.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class CountryDto {

    private Long id;
    private String name;
    private List<String> leagues;

}
