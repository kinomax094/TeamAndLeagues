package pl.com.b2bnetwork.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.b2bnetwork.football.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
