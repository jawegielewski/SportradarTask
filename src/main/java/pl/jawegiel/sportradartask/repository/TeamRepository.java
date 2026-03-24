package pl.jawegiel.sportradartask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.model.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamByOfficialName(String name);
}