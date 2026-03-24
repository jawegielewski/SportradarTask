package pl.jawegiel.sportradartask.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.service.TeamService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/save-team")
    public void saveTeam(@RequestBody Team team) {
        log.info("Adding team: {}", team.toString());
        teamService.save(team);
        log.info("Team successfully added");
    }

    @GetMapping("/find-all-teams")
    public ResponseEntity<List<Team>> findAllTeams() {
        log.info("Finding all teams");
        List<Team> teams = teamService.findAll();
        log.info("Teams found successfully");
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/find-single-team/{name}")
    public Team findOneTeam(@PathVariable String name) {
        log.info("Finding team with name: {}", name);
        Optional<Team> optionalMatch = teamService.findByOfficialName(name);
        if (optionalMatch.isPresent()) {
            log.info("Match with given name: {} found successfully", name);
            return optionalMatch.get();
        } else {
            log.info("Match with given name: {} does not exist.", name);
            return null;
        }
    }
}
