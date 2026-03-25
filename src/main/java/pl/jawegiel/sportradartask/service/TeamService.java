package pl.jawegiel.sportradartask.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.repository.TeamRepository;
import pl.jawegiel.sportradartask.util.VenueType;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TeamService {

    public final TeamRepository teamRepository;

    public void save(Team team) {
        log.info("Adding team: {}", team.toString());
        teamRepository.save(team);
        log.info("Team successfully added");
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findByOfficialName(String name) {
        return teamRepository.findTeamByOfficialName(name);
    }

    public boolean areTeamsFound(Match match) {
        return match.getHomeTeam() != null && match.getAwayTeam() != null;
    }

    public void processTeam(Match match, String teamName, VenueType venueType) {
        Optional<Team> optionalTeam = findByOfficialName(teamName);
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