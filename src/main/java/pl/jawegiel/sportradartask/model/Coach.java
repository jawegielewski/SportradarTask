package pl.jawegiel.sportradartask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tbl_coach")
public class Coach implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "first_name")
    @Size(min = 3, max = 50, message = "First name must be of length between 3-50")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 50, message = "Last name must be of length between 3-50")
    private String lastName;

    @OneToOne(mappedBy = "coach")
    @JsonIgnore
    private Team team;
}