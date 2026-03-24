package pl.jawegiel.sportradartask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@Controller
public class HelloController {

    @GetMapping
    ResponseEntity<Void> redirect() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/raw"))
                .build();
    }

    @GetMapping("/raw")
    public String index() {
        return "raw";
    }
}
