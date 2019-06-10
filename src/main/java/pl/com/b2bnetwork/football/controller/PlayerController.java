package pl.com.b2bnetwork.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.b2bnetwork.football.dto.PlayerDto;
import pl.com.b2bnetwork.football.dto.QueryArgsForFindPlayer;
import pl.com.b2bnetwork.football.service.impl.CountryService;
import pl.com.b2bnetwork.football.service.impl.PlayerService;
import pl.com.b2bnetwork.football.service.impl.TeamService;
import pl.com.b2bnetwork.football.validation.PlayerValidation;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class PlayerController {

    @Autowired
    private PlayerService service;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/secured/user/players/showPlayer/{id}")
    public String showPlayer(final Model model, @PathVariable final Long id) {
        model.addAttribute("player", service.findById(id));
        return "/showPlayer";
    }

    @GetMapping("/secured/user/players/showPlayers")
    public String showPlayers(final Model model,
                              @RequestParam("page") final Optional<Integer> page,
                              @RequestParam("size") final Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(50);

        Page<PlayerDto> playerPage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize), new QueryArgsForFindPlayer());
        model.addAttribute("playerPage", playerPage);
        model.addAttribute("Args", new QueryArgsForFindPlayer());
        int totalPages = playerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/playerList";
    }

    @GetMapping("/secured/admin/players/addPlayer")
    public String addPlayer(final Model model) {
        model.addAttribute("Player", new PlayerDto());
        model.addAttribute("types", teamService.showTeams());
        model.addAttribute("country", countryService.findAll());
        return "addPlayer";
    }

    @GetMapping("/secured/admin/players/editPlayer/{id}")
    public String editPlayer(final Model model, @PathVariable final Long id) {
        PlayerDto player = service.findById(id);
        model.addAttribute("Player", player);
        model.addAttribute("player", player);
        model.addAttribute("types", teamService.showTeams());
        model.addAttribute("country", countryService.findAll());
        return "/editPlayer";
    }


    @PostMapping("/secured/admin/players/savePlayer")
    public String savePlayer(final Model model, @ModelAttribute @Valid final PlayerDto playerDto, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            PlayerValidation.objectPlayerDtoValidation(playerDto);
            service.save(playerDto);

            return showPlayers(model, Optional.of(1), Optional.of(10));
        } else {
            model.addAttribute("Player", new PlayerDto());
            return "/addPlayer";
        }
    }

    @PostMapping("/secured/user/players/searchPlayer")
    public String searchPlayer(final Model model,
                               @ModelAttribute @Valid final QueryArgsForFindPlayer queryArgsForFindPlayer,
                               final BindingResult bindingResult,
                               @RequestParam("page") final Optional<Integer> page,
                               @RequestParam("size") final Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(50);

        if (!bindingResult.hasErrors()) {
            PlayerValidation.objectForFindPlayerValidation(queryArgsForFindPlayer);
            Page<PlayerDto> playerPage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize), queryArgsForFindPlayer);
            model.addAttribute("playerPage", playerPage);
            model.addAttribute("Args", queryArgsForFindPlayer);
            int totalPages = playerPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            return "/playerList";
        } else {
            return showPlayers(model, Optional.of(1), Optional.of(50));
        }
    }

    @GetMapping("/secured/admin/players/deletePlayer/{id}")
    public String deletePlayer(final Model model,
                               @PathVariable final Long id,
                               @ModelAttribute @Valid final QueryArgsForFindPlayer queryArgsForFindPlayer) {
        service.delete(id);
        return showPlayers(model, Optional.of(1), Optional.of(50));
    }
}

