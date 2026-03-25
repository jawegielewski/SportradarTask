package pl.jawegiel.sportradartask.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.dto.TeamRequest;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.service.CoachService;
import pl.jawegiel.sportradartask.service.TeamService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class TeamRestController {

    private final TeamService teamService;
    private final CoachService coachService;

    @PostMapping("/save-team-raw")
    public void saveTeam(@RequestBody TeamRequest teamRequest) {
        coachService.processCoach(teamRequest.getTeam(), teamRequest.getCoachFirstName(), teamRequest.getCoachLastName());

        if (coachService.isCoachFound(teamRequest.getTeam())) {
            teamService.save(teamRequest.getTeam());
        }
    }

    @GetMapping("/find-all-teams-raw")
    public ResponseEntity<List<Team>> findAllTeams() {
        log.info("Finding all teams");
        List<Team> teams = teamService.findAll();
        log.info("Teams found successfully");
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/find-single-team-raw/{name}")
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