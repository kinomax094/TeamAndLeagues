package pl.com.b2bnetwork.football.mapper;

import pl.com.b2bnetwork.football.constant.PlayersTypes;
import pl.com.b2bnetwork.football.domain.Player;
import pl.com.b2bnetwork.football.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public PlayerDto mapperPlayerToDto(final Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setNumber(player.getNumber());
        playerDto.setPlayerCountry(player.getPlayerCountry());
        playerDto.setType(player.getType().getValue());
        playerDto.setAge(player.getAge());
        playerDto.setMatchPlayed(player.getMatchPlayed());
        playerDto.setGoals(player.getGoals());
        playerDto.setYellowCards(player.getYellowCards());
        playerDto.setRedCards(player.getRedCards());
        if (player.getTeam() != null) {
            playerDto.setTeamId(player.getTeam().getId());
        }
        if (player.getTeam() != null) {
            playerDto.setTeamName(player.getTeam().getName());
        }
        return playerDto;
    }

    public Player mapperDtoToPlayer(final PlayerDto playerDto) {
        Player player = new Player();
        if (playerDto.getId() != null) {
            player.setId(playerDto.getId());
        }
        player.setName(playerDto.getName());
        player.setNumber(playerDto.getNumber());
        player.setPlayerCountry(playerDto.getPlayerCountry());
        player.setType(PlayersTypes.values(playerDto.getType()));
        player.setAge(playerDto.getAge());
        player.setMatchPlayed(playerDto.getMatchPlayed());
        player.setGoals(playerDto.getGoals());
        player.setYellowCards(playerDto.getYellowCards());
        player.setRedCards(playerDto.getRedCards());
        return player;
    }

    public List<PlayerDto> mapperPlayerToDto(final List<Player> list) {

        List<PlayerDto> collect = list.stream().map(s -> mapperPlayerToDto(s)).collect(Collectors.toList());
        return collect;

    }

    public List<Player> mapperDtoToPlayer(final List<PlayerDto> list) {

        List<Player> collect = list.stream()
                .map(s -> mapperDtoToPlayer(s))
                .collect(Collectors.toList());
        return collect;
    }
}
