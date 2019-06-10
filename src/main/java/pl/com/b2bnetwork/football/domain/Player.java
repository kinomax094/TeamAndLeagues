package pl.com.b2bnetwork.football.domain;

import lombok.Getter;
import lombok.Setter;
import pl.com.b2bnetwork.football.constant.PlayersTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long playerKey;
    private String name;
    private String number;
    private String playerCountry;
    private PlayersTypes type;
    private String age;
    private String matchPlayed;
    private String goals;
    private String yellowCards;
    private String redCards;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    private Team team;

}
