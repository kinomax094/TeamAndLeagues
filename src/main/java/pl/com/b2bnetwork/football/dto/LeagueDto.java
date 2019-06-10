package pl.com.b2bnetwork.football.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueDto {

    private Long id;
    private String name;
    private Long countryId;
    private String countryName;
}
