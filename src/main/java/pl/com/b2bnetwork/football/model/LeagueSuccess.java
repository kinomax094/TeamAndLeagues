package pl.com.b2bnetwork.football.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LeagueSuccess {

    private int success;
    private List<LeagueModel> result;
}
