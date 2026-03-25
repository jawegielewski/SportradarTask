package pl.jawegiel.sportradartask.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.jawegiel.sportradartask.model.Match;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MatchRequest implements Serializable {

    @Valid
    private Match match;
    @Size(min = 3, max = 100, message = "Home team name must be of length between 3-100")
    private String homeTeamName;
    @Size(min = 3, max = 100, message = "Away team name must be of length between 3-100")
    private String awayTeamName;
}