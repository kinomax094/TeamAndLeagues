package pl.com.b2bnetwork.football.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryArgsForFindPlayer {

    private String name;
    private Integer type;
    private String country;
}
