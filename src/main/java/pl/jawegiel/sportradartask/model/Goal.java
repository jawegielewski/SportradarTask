package pl.jawegiel.sportradartask.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_goal")
@IdClass(GoalId.class)
public class Goal implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "result_id_foreignkey")
    private Result resultId;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id_foreignkey")
    private Player playerId;

    @Column(name = "time")
    private LocalTime time;
}