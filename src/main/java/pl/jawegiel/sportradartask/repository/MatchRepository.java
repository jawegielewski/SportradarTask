package pl.jawegiel.sportradartask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jawegiel.sportradartask.model.Match;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}