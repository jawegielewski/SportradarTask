package pl.jawegiel.sportradartask.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
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
    @Size(min = 3, max = 50, message = "First name must be of length between 3-50")
    private String coachFirstName;
    @Size(min = 3, max = 50, message = "Last name must be of length between 3-50")
    private String coachLastName;
}