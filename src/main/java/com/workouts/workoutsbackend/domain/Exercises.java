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
@Table(name = "EXERCISES")
public class Exercises {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EXERCISE_ID")
    private long id;

    @NotNull
    @Column(name = "EXERCISE_NAME")
    private String exerciseName;

    @NotNull
    @Column(name = "EXERCISE_DESCRIPTION")
    private String description;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Categories category;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "JOIN_EXERCISES_MUSCLES",
            joinColumns = {@JoinColumn(name = "EXERCISE_ID", referencedColumnName = "EXERCISE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MUSCLE_ID", referencedColumnName = "ID")}
    )
    private List<Muscles> muscles = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "JOIN_EXERCISES_SECONDARY_MUSCLES",
            joinColumns = {@JoinColumn(name = "EXERCISE_ID", referencedColumnName = "EXERCISE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MUSCLE_ID", referencedColumnName = "ID")}
    )
    private List<Muscles> secondaryMuscles = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "EQUIPMENT_ID")
    private Equipment equipment;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "FAVOURITE_EXERCISE_ID")
    private FavouriteExercises favouriteExercise;
}
