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
import pl.com.b2bnetwork.football.dto.TeamDto;
import pl.com.b2bnetwork.football.service.impl.TeamService;
import pl.com.b2bnetwork.football.validation.TeamValidation;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TeamController {

    @Autowired
    private TeamService service;


    @GetMapping("/secured/user/teams/showTeam/{id}")
    public String showPlayer(final Model model, @PathVariable final Long id) {
        model.addAttribute("team", service.findById(id));
        return "/showTeam";
    }

    @GetMapping("/secured/user/teams/showTeams")
    public String showTeams(final Model model,
                            @RequestParam("page") final Optional<Integer> page,
                            @RequestParam("size") final Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<TeamDto> teamPage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("teamPage", teamPage);
        int totalPages = teamPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/teamList";
    }

    @GetMapping("/secured/admin/teams/addTeam")
    public String addTeam(final Model model) {
        model.addAttribute("Team", new TeamDto());
        return "/addTeam";
    }


    @PostMapping("/secured/admin/teams/saveTeam")
    public String saveTeam(final Model model, @ModelAttribute @Valid final TeamDto teamDto, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            TeamValidation.objectTeamDtoValidation(teamDto);
            service.save(teamDto);
            return showTeams(model, Optional.of(1), Optional.of(10));
        } else {
            model.addAttribute("Team", new TeamDto());
            return "/addTeam";
        }
    }


    @GetMapping("/secured/admin/teams/editTeam/{id}")
    public String editTeam(final Model model, @PathVariable final Long id) {
        TeamDto team = service.findById(id);
        model.addAttribute("Team", team);
        model.addAttribute("team", team);
        return "/editTeam";
    }

    @GetMapping("/secured/admin/teams/deleteTeam/{id}")
    public String deleteTeam(final Model model, @PathVariable final Long id) {
        service.delete(id);
        model.addAttribute("teams", service.showTeams());
        return showTeams(model, Optional.of(1), Optional.of(10));
    }


}
