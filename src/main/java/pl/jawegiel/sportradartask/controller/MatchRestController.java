package pl.jawegiel.sportradartask.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.dto.MatchRequest;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.service.MatchService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class MatchRestController {

    private final MatchService matchService;

    @PostMapping("/save-match-raw")
    public void saveMatch(@RequestBody MatchRequest matchRequest) {
        matchService.save(matchRequest);
    }

    @GetMapping("/find-all-matches-raw")
    @ResponseBody
    public ResponseEntity<List<Match>> findAllMatches() {
        log.info("Finding all matches");
        List<Match> matches = matchService.findAll();
        log.info("Matches found successfully");
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/find-single-match-raw/{id}")
    public Match findOne(@PathVariable Long id) {
        log.info("Finding match with id: {}", id);
        Optional<Match> optionalMatch = matchService.findById(id);
        if (optionalMatch.isPresent()) {
            log.info("Match with given id: {} found successfully", id);
            return optionalMatch.get();
        } else {
            log.info("Match with given id: {} does not exist.", id);
            return null;
        }
    }
}