package pl.jawegiel.sportradartask.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.dto.MatchRequest;
import pl.jawegiel.sportradartask.model.Coach;
import pl.jawegiel.sportradartask.service.CoachService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/save-coach/{isRedirected}")
    public String saveCoach(@PathVariable boolean isRedirected, Model model) {
        Coach coach = new Coach();
        model.addAttribute("coach", coach);
        model.addAttribute("isRedirected", isRedirected);
        return "save_coach";
    }

    @PostMapping("/save-coach")
    public String saveCoach(@Valid @ModelAttribute("coach") Coach coach, BindingResult result) {

        if (result.hasErrors()) {
            return "save_coach";
        }

        log.info("Adding coach: {}", coach.toString());
        coachService.save(coach);
        log.info("Coach successfully added");
        return "main";
    }
}
