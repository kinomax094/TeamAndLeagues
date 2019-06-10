package pl.com.b2bnetwork.football.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {

    private Long id;
    private String name;
    private String number;
    private String playerCountry;
    private String type;
    private String age;
    private String matchPlayed;
    private String goals;
    private String yellowCards;
    private String redCards;
    private String teamName;
    private Long teamId;
}
