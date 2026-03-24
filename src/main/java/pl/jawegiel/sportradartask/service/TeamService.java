package pl.jawegiel.sportradartask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {

    public final TeamRepository teamRepository;

    public void save(Team team) {
        teamRepository.save(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findByOfficialName(String name) {
        return teamRepository.findTeamByOfficialName(name);
    }
}