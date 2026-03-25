package pl.jawegiel.sportradartask.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jawegiel.sportradartask.model.Coach;
import pl.jawegiel.sportradartask.service.CoachService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class CoachRestController {

    private final CoachService coachService;

    @PostMapping("/save-coach-raw")
    public void saveCoach(@RequestBody Coach coach) {

        log.info("Adding coach: {}", coach.toString());
        coachService.save(coach);
        log.info("Coach successfully added");
    }

    @GetMapping("/find-all-coaches-raw")
    public ResponseEntity<List<Coach>> findAllCoach() {
        log.info("Finding all coaches");
        List<Coach> coaches = coachService.findAll();
        log.info("Coaches found successfully");
        return ResponseEntity.ok(coaches);
    }

    @GetMapping("/find-single-coach-raw/{firstName}/{lastName}")
    public Coach findOneCoach(@PathVariable String firstName, @PathVariable String lastName) {
        log.info("Finding coach with full name: {} {}", firstName, lastName);
        Optional<Coach> optionalCoach = coachService.findByFirstNameAndLastNAme(firstName, lastName);
        if (optionalCoach.isPresent()) {
            log.info("Coach with given full name: {} {} found successfully", firstName, lastName);
            return optionalCoach.get();
        } else {
            log.info("Coach with given full name: {} {} does not exist.", firstName, lastName);
            return null;
        }
    }
}