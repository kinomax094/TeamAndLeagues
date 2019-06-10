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
import pl.com.b2bnetwork.football.domain.League;
import pl.com.b2bnetwork.football.dto.LeagueDto;
import pl.com.b2bnetwork.football.service.impl.CountryService;
import pl.com.b2bnetwork.football.service.impl.LeagueService;
import pl.com.b2bnetwork.football.validation.LeagueValidation;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class LeagueController {

    @Autowired
    private LeagueService service;

    @Autowired
    private CountryService countryService;

    @GetMapping("/secured/user/leagues/showLeagues")
    public String findAll(final Model model,
                          @RequestParam("page") final Optional<Integer> page,
                          @RequestParam("size") final Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(50);

        Page<League> leaguePage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("leaguePage", leaguePage);
        int totalPages = leaguePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "leagueList";
    }

    @GetMapping("/secured/user/leagues/{leagueId}")
    public String findOneById(final Model model, @PathVariable final Long leagueId) {
        model.addAttribute("league", service.findOneById(leagueId));
        return "/showLeague";
    }

    @GetMapping("/secured/admin/leagues/addLeague")
    public String addLeague(final Model model) {
        model.addAttribute("league", new LeagueDto());
        model.addAttribute("countries", countryService.findAll());
        return "/addleague";
    }

    @GetMapping("/secured/admin/leagues/editLeague/{leagueId}")
    public String editLeague(final Model model, @PathVariable final Long leagueId) {
        LeagueDto league = service.findOneById(leagueId);
        model.addAttribute("league", league);
        model.addAttribute("countries", countryService.findAll());
        return "/editLeague";
    }


    @PostMapping("/secured/admin/leagues/saveLeague")
    public String saveLeague(final Model model, @ModelAttribute @Valid final LeagueDto leagueDto, final BindingResult bindingResult) {
        LeagueValidation.leagueDtoValidation(leagueDto);
        service.save(leagueDto);
        return "redirect:/secured/user/leagues/showLeagues";
    }

    @GetMapping("/secured/admin/leagues/deleteLeague/{leagueId}")
    public String deleteLeague(final Model model, @PathVariable final Long leagueId) {
        service.delete(leagueId);
        return "redirect:/secured/user/leagues/showLeagues";
    }
}
