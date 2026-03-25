package pl.jawegiel.sportradartask.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tbl_team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = "Name must be of length between 3-50")
    private String name;

    @Column(name = "official_name")
    @Size(min = 3, max = 100, message = "Official name must be of length between 3-100")
    private String officialName;

    @Column(name = "slug")
    @Size(min = 3, max = 100, message = "Slug must be of length between 3-100")
    private String slug;

    @Column(name = "abbreviation")
    @Size(min = 3, max = 3, message = "Abbreviation must be of length 3")
    private String abbreviation;

    @Column(name = "country_code")
    @Size(min = 2, max = 3, message = "Country code must be of length between 2-3")
    private String countryCode;

    @Column(name = "stage_position")
    private String stagePosition;

    @Column(name = "year_of_foundation")
    @NotNull(message = "Year of foundation is required")
    @Min(value = 1850, message = "There were no clubs below 1850")
    @Max(value = 2026, message = "Year above 2026 did not come yet")
    private Short yearOfFoundation;

    @OneToOne
    @JoinColumn(name = "coach_id_foreignkey", referencedColumnName = "coach_id")
    @Valid
    private Coach coach;

    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    @OneToMany
    @JoinColumn(name = "team_id_foreignkey")
    private List<TeamPlayers> teamPlayers;
}