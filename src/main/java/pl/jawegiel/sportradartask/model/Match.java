package pl.jawegiel.sportradartask.model;

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
@Entity
@Table(name = "tbl_match")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private long matchId;

    @Column(name = "discipline")
    private String discipline;

    @Column(name = "season")
    private short season;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MatchStatus status;

    @Column(name = "date_venue")
    private LocalDate dateVenue;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "time_venue_utc")
    private LocalTime timeVenueUTC;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "group_col")
    private String groupCol;

    @ManyToOne
    @JoinColumn(name = "home_team_id_foreignkey", referencedColumnName = "team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id_foreignkey", referencedColumnName = "team_id")
    private Team awayTeam;
}