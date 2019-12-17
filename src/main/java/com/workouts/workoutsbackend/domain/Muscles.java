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
@Table(name = "MUSCLES")
public class Muscles {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "EXTERNAL_ID")
    private Long externalId;

    @NotNull
    @Column(name = "MUSCLE_NAME")
    private String muscleName;

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "muscles")
    private List<Exercises> exercises = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "secondaryMuscles")
    private List<Exercises> exercisesWithSecondaryMuscles = new ArrayList<>();
}
