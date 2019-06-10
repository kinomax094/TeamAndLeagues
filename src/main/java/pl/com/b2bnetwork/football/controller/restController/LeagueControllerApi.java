package pl.com.b2bnetwork.football.controller.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.b2bnetwork.football.exception.NotFoundException;
import pl.com.b2bnetwork.football.model.LeagueModel;
import pl.com.b2bnetwork.football.model.LeagueSuccess;
import pl.com.b2bnetwork.football.service.restService.LeagueServiceApiImpl;

@RestController()
@RequiredArgsConstructor
public class LeagueControllerApi {

    private final LeagueServiceApiImpl leagueServiceApiImpl;

    @GetMapping(path = "/getAllLeagues")
    public LeagueSuccess getAllLeagues() {
        final LeagueSuccess result = leagueServiceApiImpl.getLeagues();
        return result;
    }

    @GetMapping(path = "/exception")
    public void exception() {
        throw new NotFoundException("");
    }

    @GetMapping(path = "/getLeagueByName/{leagueName}")
    public LeagueModel getLeagueByName(@PathVariable final String leagueName, @RequestBody final LeagueModel leagueModel) {
        return leagueServiceApiImpl.getLeaguByName(leagueName, leagueModel);
    }


}
