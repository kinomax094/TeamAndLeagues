package pl.com.b2bnetwork.football.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamKey;
    private String name;
    private String logo;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Player> players;
}
