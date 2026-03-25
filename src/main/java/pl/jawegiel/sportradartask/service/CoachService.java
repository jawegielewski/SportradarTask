package pl.jawegiel.sportradartask.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jawegiel.sportradartask.model.Coach;
import pl.jawegiel.sportradartask.model.Team;
import pl.jawegiel.sportradartask.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CoachService {

    public final CoachRepository coachRepository;

    public void save(Coach coach) {
        log.info("Adding coach: {}", coach.toString());
        coachRepository.save(coach);
        log.info("Coach successfully added");
    }

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findByFirstNameAndLastNAme(String firstName, String lastName) {
        return coachRepository.findCoachByFirstNameAndLastName(firstName, lastName);
    }

    public boolean isCoachFound(Team team) {
        return team.getCoach() != null;
    }

    public void processCoach(Team team, String coachFirstName, String coachLastName) {
        log.info("Retrieving coach: {} {}", coachFirstName, coachLastName);
        Optional<Coach> optionalCoach = findByFirstNameAndLastNAme(coachFirstName, coachLastName);
        if (optionalCoach.isEmpty()) {
            log.info("Coach named: {} {} not found", coachFirstName, coachLastName);
        } else {
            log.info("Coach named: {} {} successfully retrieved", coachFirstName, coachLastName);
            team.setCoach(optionalCoach.get());
        }
    }
}