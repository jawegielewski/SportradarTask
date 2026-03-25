package pl.jawegiel.sportradartask.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.dto.TeamRequest;
import pl.jawegiel.sportradartask.model.Coach;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.repository.TeamRepository;
import pl.jawegiel.sportradartask.service.CoachService;
import pl.jawegiel.sportradartask.service.TeamService;
import pl.jawegiel.sportradartask.util.VenueType;

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

        log.info("Retrieving coach: {} {}", teamRequest.getCoachFirstName(), teamRequest.getCoachLastName());
        processCoach(teamRequest.getTeam(), teamRequest.getCoachFirstName(), teamRequest.getCoachLastName());

        if (isCoachFound(teamRequest.getTeam())) {
            log.info("Adding team: {}", teamRequest.getTeam().toString());
            teamService.save(teamRequest.getTeam());
            log.info("Team successfully added");
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

    private boolean isCoachFound(Team team) {
        return team.getCoach() != null;
    }

    private void processCoach(Team team, String coachFirstName, String coachLastName) {
        Optional<Coach> optionalCoach = coachService.findByFirstNameAndLastNAme(coachFirstName, coachLastName);
        if (optionalCoach.isEmpty()) {
            log.info("Coach named: {} {} not found", coachFirstName, coachLastName);
        } else {
            log.info("Coach named: {} {} successfully retrieved", coachFirstName, coachLastName);
            team.setCoach(optionalCoach.get());
        }
    }
}
