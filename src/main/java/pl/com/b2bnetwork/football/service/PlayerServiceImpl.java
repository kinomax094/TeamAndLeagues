package pl.com.b2bnetwork.football.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.b2bnetwork.football.domain.Player;
import pl.com.b2bnetwork.football.domain.Team;
import pl.com.b2bnetwork.football.dto.PlayerDto;
import pl.com.b2bnetwork.football.dto.QueryArgsForFindPlayer;
import pl.com.b2bnetwork.football.exception.NotFoundException;
import pl.com.b2bnetwork.football.mapper.PlayerMapper;
import pl.com.b2bnetwork.football.repository.PlayerRepository;
import pl.com.b2bnetwork.football.repository.QueryRepositoryPlayerImpl;
import pl.com.b2bnetwork.football.repository.TeamRepository;
import pl.com.b2bnetwork.football.service.impl.PlayerService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;
    @Autowired
    private TeamRepository repositoryTeam;

    @Autowired
    private QueryRepositoryPlayerImpl queryRepository;

    @Override
    public List<PlayerDto> getAllPlayers() {
        PlayerMapper mapper = new PlayerMapper();
        List<PlayerDto> result = mapper.mapperPlayerToDto(repository.findAll());
        return result;
    }

    @Override
    public PlayerDto findById(final Long id) {
        PlayerDto result = null;
        PlayerMapper mapper = new PlayerMapper();
        Optional<Player> optional = repository.findById(id);
        if (!optional.isPresent()) {

            throw new NotFoundException("Nie znaleziono Zawodnika");
        } else {
            result = mapper.mapperPlayerToDto(optional.get());
        }
        return result;

    }

    @Override
    public void delete(final Long id) {
        Optional<Player> optional = repository.findById(id);
        if (!optional.isPresent()) {

            throw new NotFoundException("Nie znaleziono Zawodnika");

        } else {
            repository.delete(optional.get());
        }
    }

    @Override
    public void save(final PlayerDto playerDto) {
        PlayerMapper mapper = new PlayerMapper();
        if (playerDto.getId() != null && repository.existsById(playerDto.getId())) {
            Optional<Player> one = repository.findById(playerDto.getTeamId());
            Player two = mapper.mapperDtoToPlayer(playerDto);
            two.setTeam(one.get().getTeam());
            repository.save(two);
        } else {
            Player player = mapper.mapperDtoToPlayer(playerDto);
            Long typeId = playerDto.getTeamId();

            Optional<Team> optionalTeam = repositoryTeam.findById(typeId);
            if (!optionalTeam.isPresent()) {
                throw new NotFoundException("Nie znalezion takiej druzyny");
            }
            Team team = optionalTeam.get();
            team.getPlayers().add(player);
            player.setTeam(team);
            repository.save(player);
        }
    }

    @Override
    public List<PlayerDto> findPlayers(final QueryArgsForFindPlayer queryArgsForFindPlayer) {
        List<PlayerDto> result = null;
        Optional<List<Player>> players = queryRepository.findPlayers(queryArgsForFindPlayer);
        PlayerMapper mapper = new PlayerMapper();

        if (!players.isPresent()) {
            throw new NotFoundException("Nie znaleziono gracza");
        } else {
            result = mapper.mapperPlayerToDto(players.get());
        }
        return result;
    }

    @Override
    public Page<PlayerDto> findPaginated(final Pageable pageable, final QueryArgsForFindPlayer query) {
        List<PlayerDto> listFindPlayers = findPlayers(query);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PlayerDto> list;

        if (listFindPlayers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listFindPlayers.size());
            list = listFindPlayers.subList(startItem, toIndex);
        }
        Page<PlayerDto> playerDtoPage
                = new PageImpl<PlayerDto>(list, PageRequest.of(currentPage, pageSize), listFindPlayers.size());

        return playerDtoPage;
    }


}
