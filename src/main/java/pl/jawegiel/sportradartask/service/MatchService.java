package pl.jawegiel.sportradartask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.repository.MatchRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {

    public final MatchRepository matchRepository;

    public void save(Match match) {
        matchRepository.save(match);
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public Optional<Match> findById(long matchId) {
        return matchRepository.findById(matchId);
    }
}