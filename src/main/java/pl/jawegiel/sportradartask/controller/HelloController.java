package pl.jawegiel.sportradartask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String mainUserFriendly() {
        return "main";
    }

    @GetMapping("/raw")
    public String raw() {
        return "raw";
    }
}