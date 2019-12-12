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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "EXTERNAL_ID")
    private long externalId;

    @NotNull
    @Column(name = "MUSCLE_NAME")
    private String muscle_name;

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "muscles")
    private List<Exercises> exercises = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "secondaryMuscles")
    private List<Exercises> exercisesWithSecondaryMuscles = new ArrayList<>();
}
