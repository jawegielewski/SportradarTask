package pl.jawegiel.sportradartask.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jawegiel.sportradartask.model.Coach;
import pl.jawegiel.sportradartask.service.CoachService;

@Controller
@AllArgsConstructor
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

        coachService.save(coach);

        return "redirect:/main";
    }
}