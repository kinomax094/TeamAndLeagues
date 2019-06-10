package pl.com.b2bnetwork.football.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long countryKey;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "country", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<League> leagues;
}
