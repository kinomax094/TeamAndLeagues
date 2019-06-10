package pl.com.b2bnetwork.football.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.com.b2bnetwork.football.model.TeamSuccess;
import pl.com.b2bnetwork.football.service.restService.TeamServiceApiImpl;


@RestController
public class TeamControllerApi {

    @Autowired
    private TeamServiceApiImpl teamServiceApiImpl;

    @GetMapping(path = "/getTeamBody")
    public TeamSuccess getTeamsBody(@RequestParam final String id) {
        final TeamSuccess result = teamServiceApiImpl.getTeam(id);
        return result;
    }


}
