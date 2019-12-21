package com.workouts.workoutsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EQUIPMENT")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "EXTERNAL_ID", unique = true)
    private Integer externalId;

    @NotNull
    @Column(name = "NAME", unique = true)
    private String equipmentName;

    @OneToMany(
            targetEntity = Exercises.class,
            mappedBy = "equipment",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Exercises> exercises = new ArrayList<>();

    public Equipment(Integer externalId, String equipmentName) {
        this.externalId = externalId;
        this.equipmentName = equipmentName;
    }
}
