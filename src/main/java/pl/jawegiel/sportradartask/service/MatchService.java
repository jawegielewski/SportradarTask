package pl.jawegiel.sportradartask.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jawegiel.sportradartask.dto.MatchRequest;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.repository.MatchRepository;
import pl.jawegiel.sportradartask.util.VenueType;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MatchService {

    public final MatchRepository matchRepository;
    private final TeamService teamService;

    public void save(Match match) {
        matchRepository.save(match);
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public Optional<Match> findById(long matchId) {
        return matchRepository.findById(matchId);
    }

    public void save(MatchRequest matchRequest) {
            log.info("Retrieving home team: {}", matchRequest.getHomeTeamName());
        teamService.processTeam(matchRequest.getMatch(), matchRequest.getHomeTeamName(), VenueType.HOME);

        log.info("Retrieving away team: {}", matchRequest.getAwayTeamName());
        teamService.processTeam(matchRequest.getMatch(), matchRequest.getAwayTeamName(), VenueType.AWAY);

        if (teamService.areTeamsFound(matchRequest.getMatch())) {
            log.info("Adding match: {}", matchRequest.getMatch().toString());
            save(matchRequest.getMatch());
            log.info("Match successfully added");
        } else {
            log.info("Match not added!");
        }
    }
}