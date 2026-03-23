package pl.jawegiel.sportradartask.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_team_players")
@IdClass(TeamPlayersId.class)
public class TeamPlayers implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id_foreignkey")
    private Team teamId;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id_foreignkey")
    private Player playerId;
}