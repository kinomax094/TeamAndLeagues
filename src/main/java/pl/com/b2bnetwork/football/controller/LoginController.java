package pl.com.b2bnetwork.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }


}


