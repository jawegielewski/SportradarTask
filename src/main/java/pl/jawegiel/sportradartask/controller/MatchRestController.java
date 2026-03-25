package pl.jawegiel.sportradartask.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.dto.MatchRequest;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.service.MatchService;
import pl.jawegiel.sportradartask.service.TeamService;
import pl.jawegiel.sportradartask.util.VenueType;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class MatchRestController {

    private final MatchService matchService;
    private final TeamService teamService;

    @PostMapping("/save-match-raw")
    public void saveMatch(@RequestBody MatchRequest matchRequest) {

        log.info("Retrieving home team: {}", matchRequest.getHomeTeamName());
        processTeam(matchRequest.getMatch(), matchRequest.getHomeTeamName(), VenueType.HOME);

        log.info("Retrieving away team: {}", matchRequest.getAwayTeamName());
        processTeam(matchRequest.getMatch(), matchRequest.getAwayTeamName(), VenueType.AWAY);

        if (areTeamsFound(matchRequest.getMatch())) {
            log.info("Adding match: {}", matchRequest.getMatch().toString());
            matchService.save(matchRequest.getMatch());
            log.info("Match successfully added");
        }
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

    private boolean areTeamsFound(Match match) {
        return match.getHomeTeam() != null && match.getAwayTeam() != null;
    }

    private void processTeam(Match match, String teamName, VenueType venueType) {
        Optional<Team> optionalTeam = teamService.findByOfficialName(teamName);
        if (optionalTeam.isEmpty()) {
            log.info("Team named: {} not found", teamName);
        } else {
            log.info("Team named: {} successfully retrieved", teamName);
            switch (venueType) {
                case HOME -> match.setHomeTeam(optionalTeam.get());
                case AWAY -> match.setAwayTeam(optionalTeam.get());
            }
        }
    }
}
