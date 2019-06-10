package pl.com.b2bnetwork.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.b2bnetwork.football.domain.Country;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findOneByName(String countryName);
}
