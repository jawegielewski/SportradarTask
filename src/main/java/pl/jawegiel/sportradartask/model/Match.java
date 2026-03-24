package pl.jawegiel.sportradartask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private String discipline;

    @Column(name = "season")
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
    private String stadium;

    @Column(name = "group_col")
    private String groupCol;

    @ManyToOne
    @JoinColumn(name = "home_team_id_foreignkey", referencedColumnName = "team_id")
    @JsonIgnoreProperties({"homeMatches", "awayMatches"}) // Ignore the back-links in Team
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id_foreignkey", referencedColumnName = "team_id")
    @JsonIgnoreProperties({"homeMatches", "awayMatches"}) // Ignore the back-links in Team
    private Team awayTeam;
}