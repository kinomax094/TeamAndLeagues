package pl.com.b2bnetwork.football.service.restService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.com.b2bnetwork.football.domain.modelToEntityMapper.PlayerModelToPlayerMapper;
import pl.com.b2bnetwork.football.domain.modelToEntityMapper.TeamModelToTeamMapper;
import pl.com.b2bnetwork.football.model.PlayerModel;
import pl.com.b2bnetwork.football.model.TeamModel;
import pl.com.b2bnetwork.football.model.TeamSuccess;
import pl.com.b2bnetwork.football.repository.PlayerRepository;
import pl.com.b2bnetwork.football.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceApiImpl {

    @Value("${URL_TEAMS}")
    private String urlTeam;
    @Value("${API_KEY}")
    private String apiKey;

    private static final String HEADER_NAME = "user-agent";
    private static final String HEADER_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
            + "(KHTML, like Gecko) Chrome/"
            + "54.0.2840.99 Safari/537.36";

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final RestTemplate restTemplate;


    public TeamSuccess getTeam(final String id) {
        final TeamSuccess result;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME, HEADER_VALUE);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<TeamSuccess> exchange = restTemplate.exchange(urlBuilder(id), HttpMethod.GET, httpEntity, TeamSuccess.class);
        result = exchange.getBody();
        return result;
    }

    public List<List<TeamModel>> teamModelList() {
        List<List<TeamModel>> result = new ArrayList<>();
        final int a = 200;
        for (int i = 1; i < a; i++) {
            if (getTeam(String.valueOf(i)).getResult() != null) {
                if (!getTeam(String.valueOf(i)).getResult().get(0).getPlayers().isEmpty()) {
                    result.add(getTeam(String.valueOf(i)).getResult());
                }
            }
        }
        result.sort((o1, o2) -> Long.parseLong(o1.get(0).getTeamKey()) > Long.parseLong(o2.get(0).getTeamKey()) ? 1 : -1);
        return result;
    }


    @Async("taskExecutor")
    public void saveOne(final int startFrom, final int endOn) {
        TeamModelToTeamMapper teamModelToTeamMapper = new TeamModelToTeamMapper();
        PlayerModelToPlayerMapper playerModelToPlayerMapper = new PlayerModelToPlayerMapper();

        for (int i = startFrom; i < endOn; i++) {
            if (getTeam(String.valueOf(i)).getResult() != null) {
                if (!getTeam(String.valueOf(i)).getResult().get(0).getPlayers().isEmpty()) {
                    teamRepository.save(teamModelToTeamMapper.mapper(getTeam(String.valueOf(i)).getResult().get(0)));
                    for (PlayerModel player : getTeam(String.valueOf(i)).getResult().get(0).getPlayers()) {
                        playerRepository.save(playerModelToPlayerMapper.mapper(player, teamRepository.findOneByTeamKey(Long.parseLong(getTeam(String.valueOf(i)).getResult().get(0).getTeamKey()))));
                    }
                }
            }
        }
    }

    public void saveTeams() {
        TeamModelToTeamMapper teamModelToTeamMapper = new TeamModelToTeamMapper();
        PlayerModelToPlayerMapper playerModelToPlayerMapper = new PlayerModelToPlayerMapper();

        List<List<TeamModel>> lists = teamModelList();
        for (List<TeamModel> list : lists) {
            for (TeamModel teamModel : list) {
                teamRepository.save(teamModelToTeamMapper.mapper(teamModel));
                for (PlayerModel playerModel : teamModel.getPlayers()) {
                    playerRepository.save(playerModelToPlayerMapper.mapper(playerModel,
                            teamRepository.findOneByTeamKey(Long.parseLong(teamModel.getTeamKey()))));
                }
            }
        }
    }


    public String urlBuilder(final String id) {
        StringBuilder url = new StringBuilder();
        url.append(urlTeam);
        url.append(id);
        url.append("&APIkey=");
        url.append(apiKey);
        return url.toString();
    }


}
