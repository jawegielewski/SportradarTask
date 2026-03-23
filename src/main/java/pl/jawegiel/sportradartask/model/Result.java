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
@Table(name = "tbl_result")
public class Result implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private long resultId;

    @Column(name = "home_goals")
    private String homeGoals;

    @Column(name = "away_goals")
    private String awayGoals;

    @Column(name = "winner")
    private String winner;

    @Column(name = "message")
    private String message;

    @OneToMany
    @JoinColumn(name = "result_id_foreignkey")
    private Set<Match> matches;

    @OneToMany
    @JoinColumn(name = "result_id_foreignkey")
    private Set<Goal> goals;
}