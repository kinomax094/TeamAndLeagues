package pl.com.b2bnetwork.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.b2bnetwork.football.domain.Country;
import pl.com.b2bnetwork.football.service.impl.CountryService;
import pl.com.b2bnetwork.football.validation.CountryValidation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/secured/user/countries/showCountries")
    public String findAll(final Model model,
                          @RequestParam("page") final Optional<Integer> page,
                          @RequestParam("size") final Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(50);

        Page<Country> countryPage = countryService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("countryPage", countryPage);

        int totalPages = countryPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }
        return "/countryList";
    }

    @GetMapping("/secured/user/countries/{countryName}")
    public String findOneByName(final Model model, @PathVariable final String countryName) {
        CountryValidation.countryNameValidation(countryName);
        Country country = countryService.findOneByName(countryName);
        model.addAttribute("country", country);
        model.addAttribute("leagues", countryService.findAllLeaguesForGivenCountryId(country.getId()));

        return "/showCountry";
    }

}
