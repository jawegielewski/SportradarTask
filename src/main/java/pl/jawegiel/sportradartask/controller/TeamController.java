package pl.jawegiel.sportradartask.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jawegiel.sportradartask.dto.TeamRequest;
import pl.jawegiel.sportradartask.service.CoachService;
import pl.jawegiel.sportradartask.service.TeamService;

@Controller
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final CoachService coachService;

    @GetMapping("/save-team")
    public String saveTeam(Model model) {
        TeamRequest teamRequest = new TeamRequest();
        model.addAttribute("team_request", teamRequest);
        return "save_team";
    }

    @PostMapping("/save-team")
    public String saveTeam(@Valid @ModelAttribute("team_request") TeamRequest teamRequest, BindingResult result) {

        if (result.hasErrors()) {
            return "save_team";
        }

        coachService.processCoach(teamRequest.getTeam(), teamRequest.getCoachFirstName(), teamRequest.getCoachLastName());

        if (coachService.isCoachFound(teamRequest.getTeam())) {
            teamService.save(teamRequest.getTeam());
        } else {
            return "redirect:/save-coach/true";
        }

        return "redirect:/main";
    }
}