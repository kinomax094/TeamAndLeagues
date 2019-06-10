package pl.com.b2bnetwork.football.domain.modelToEntityMapper;

import pl.com.b2bnetwork.football.domain.Player;
import pl.com.b2bnetwork.football.domain.Team;
import pl.com.b2bnetwork.football.model.PlayerModel;

public class PlayerModelToPlayerMapper {

    public Player mapper(final PlayerModel playerModel, final Team team) {
        Player player = new Player();
        player.setPlayerKey(Long.parseLong(playerModel.getPlayerKey()));
        player.setName(playerModel.getPlayerName());
        player.setNumber(playerModel.getPlayerNumber());
        player.setPlayerCountry(playerModel.getPlayerCountry());
        player.setType(playerModel.getPlayerType());
        player.setAge(playerModel.getPlayerAge());
        player.setMatchPlayed(playerModel.getPlayerMatchPlayed());
        player.setGoals(playerModel.getPlayerGoals());
        player.setYellowCards(playerModel.getPlayerYellowCards());
        player.setRedCards(playerModel.getPlayerRedCards());
        player.setTeam(team);
        return player;
    }
}
