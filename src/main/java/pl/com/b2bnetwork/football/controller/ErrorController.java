package pl.com.b2bnetwork.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping(value = "/403")
    public String accessDenied() {
        return "errorPage";
    }

}
