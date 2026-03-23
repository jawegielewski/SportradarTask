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
@Table(name = "tbl_stage")
public class Stage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private long stageId;

    @Column(name = "name")
    private String homeGoals;

    @Column(name = "ordering")
    private String awayGoals;

    @OneToMany
    @JoinColumn(name = "stage_id_foreignkey")
    private Set<Match> matches;
}