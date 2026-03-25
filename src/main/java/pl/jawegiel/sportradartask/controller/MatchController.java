package pl.jawegiel.sportradartask.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jawegiel.sportradartask.dto.MatchRequest;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.service.MatchService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/save-match")
    public String saveMatch(Model model) {
        MatchRequest matchRequest = new MatchRequest();
        model.addAttribute("match_request", matchRequest);
        return "save_match";
    }

    @PostMapping("/save-match")
    public String saveMatch(@Valid @ModelAttribute("match_request") MatchRequest matchRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "save_match";
        }

        matchService.save(matchRequest);

        return "redirect:/main";
    }

    @GetMapping("/find-all-matches")
    public String findAllMatches(Model model) {
        List<Match> matches = matchService.findAll();
        model.addAttribute("matches", matches);
        return "find_all_matches";
    }

    @GetMapping("/find-single-match")
    public String findSingleMatch(Model model) {
        long matchId = -1;
        model.addAttribute("matchId", matchId);
        return "find_single_match";
    }

    @PostMapping("/find-single-match")
    public String findSingleMatch(@RequestParam("matchId") long matchId, RedirectAttributes redirectAttributes) {
        if (matchId <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Match ID must be a positive number");
            return "redirect:/find-single-match";
        }
        Optional<Match> optionalMatch = matchService.findById(matchId);
        if (optionalMatch.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Match with such id does not exist.");
            return "redirect:/find-single-match";
        } else {
            redirectAttributes.addFlashAttribute("match", optionalMatch.get());
            return "redirect:/find-single-match/" + matchId;
        }
    }

    @GetMapping("/find-single-match/{matchId}")
    public String findSingleMatch(Model model, @PathVariable long matchId) {
        return "find_single_match";
    }
}