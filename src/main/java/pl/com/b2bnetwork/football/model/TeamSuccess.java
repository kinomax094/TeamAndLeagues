package pl.com.b2bnetwork.football.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TeamSuccess {

    private int success;
    private List<TeamModel> result;
}
