package pl.jawegiel.sportradartask.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "official_name")
    private String officialName;

    @Column(name = "slug")
    private String slug;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "stage_position")
    private String stagePosition;

    @Column(name = "coach")
    private String coach;

    @Column(name = "year_of_foundation")
    private short yearOfFoundation;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Match> awayMatches;

    @OneToMany
    @JoinColumn(name = "team_id_foreignkey")
    private Set<TeamPlayers> teamPlayers;
}