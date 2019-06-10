package pl.com.b2bnetwork.football.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDto {

    private Long id;
    private String name;
    private String logo;
    private List<PlayerDto> players;
}
