package pl.jawegiel.sportradartask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jawegiel.sportradartask.model.Coach;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findCoachByFirstNameAndLastName(String firstName, String lastName);
}