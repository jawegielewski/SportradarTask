package pl.jawegiel.sportradartask.dto;

import jakarta.validation.Valid;
import lombok.*;
import pl.jawegiel.sportradartask.model.Match;
import pl.jawegiel.sportradartask.model.Team;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TeamRequest implements Serializable {
    @Valid
    private Team team;
    private String coachFirstName;
    private String coachLastName;
}
