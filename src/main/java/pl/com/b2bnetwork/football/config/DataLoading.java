package pl.com.b2bnetwork.football.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import pl.com.b2bnetwork.football.FootballApp;
import pl.com.b2bnetwork.football.service.restService.CountryServiceApiImpl;
import pl.com.b2bnetwork.football.service.restService.LeagueServiceApiImpl;
import pl.com.b2bnetwork.football.service.restService.TeamServiceApiImpl;

@Component
@RequiredArgsConstructor
public class DataLoading implements ApplicationRunner {

    private final CountryServiceApiImpl countryServiceApiImpl;
    private final LeagueServiceApiImpl leagueServiceApiImpl;
    private final TeamServiceApiImpl teamServiceApiImpl;

    public static void main(final String[] args) {
        SpringApplication.run(FootballApp.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {

        countryServiceApiImpl.saveAllCountries();
        leagueServiceApiImpl.saveAllLeagues();
        teamServiceApiImpl.saveOne(1, 20);
        teamServiceApiImpl.saveOne(20, 40);
        teamServiceApiImpl.saveOne(40, 60);
        teamServiceApiImpl.saveOne(60, 80);
        teamServiceApiImpl.saveOne(80, 90);
        teamServiceApiImpl.saveOne(100, 200);
        teamServiceApiImpl.saveOne(200, 300);
        teamServiceApiImpl.saveOne(300, 400);
        teamServiceApiImpl.saveOne(400, 500);
        teamServiceApiImpl.saveOne(500, 700);
        teamServiceApiImpl.saveOne(700, 900);
        teamServiceApiImpl.saveOne(900, 1000);
        teamServiceApiImpl.saveOne(1000, 1200);
        teamServiceApiImpl.saveOne(1200, 1500);
        teamServiceApiImpl.saveOne(1500, 2000);
    }

}
