package pl.jawegiel.sportradartask.dto;

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
    private Match match;
    private String homeTeamName;
    private String awayTeamName;
}
