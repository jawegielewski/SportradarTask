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
@Table(name = "tbl_origin_competition")
public class OriginCompetition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origin_competition_id")
    private long originCompetitionId;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "origin_competition_id_foreignkey")
    private Set<Match> matches;
}