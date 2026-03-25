package pl.jawegiel.sportradartask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.jawegiel.sportradartask.util.DayOfWeek;
import pl.jawegiel.sportradartask.util.MatchStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tbl_match")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "discipline")
    @Size(min = 3, max = 50, message = "Discipline must be of length between 3-50")
    private String discipline;

    @Column(name = "season")
    @NotNull(message = "Season is required")
    @Min(value = 1850, message = "There were no seasons below 1850")
    @Max(value = 2026, message = "Year above 2026 did not come yet")
    private Short season;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MatchStatus status;

    @Column(name = "date_venue")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateVenue;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "time_venue_utc")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeVenueUTC;

    @Column(name = "stadium")
    @Size(min = 3, max = 50, message = "Stadium must be of length between 3-50")
    private String stadium;

    @Column(name = "group_col")
    private String groupCol;

    @ManyToOne
    @JoinColumn(name = "home_team_id_foreignkey", referencedColumnName = "team_id")
    @JsonIgnoreProperties({"homeMatches", "awayMatches"}) // Ignore the back-links in Team
    @Valid
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id_foreignkey", referencedColumnName = "team_id")
    @JsonIgnoreProperties({"homeMatches", "awayMatches"}) // Ignore the back-links in Team
    @Valid
    private Team awayTeam;
}