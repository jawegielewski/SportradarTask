package pl.jawegiel.sportradartask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jawegiel.sportradartask.model.Coach;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachService {

    public final CoachRepository coachRepository;

    public void save(Coach coach) {
        coachRepository.save(coach);
    }

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findByFirstNameAndLastNAme(String firstName, String lastName) {
        return coachRepository.findCoachByFirstNameAndLastName(firstName, lastName);
    }
}