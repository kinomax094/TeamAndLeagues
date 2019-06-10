package pl.com.b2bnetwork.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.b2bnetwork.football.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findOneByTeamKey(Long teamKey);
}
