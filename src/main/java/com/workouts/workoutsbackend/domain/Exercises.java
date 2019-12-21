package com.workouts.workoutsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @Column(name = "EXERCISE_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "EXERCISE_NAME", unique = true)
    private String exerciseName;

    @NotNull
    @Column(name = "EXERCISE_DESCRIPTION", length = 3000)
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

    @OneToMany(
            targetEntity = FavouriteExercises.class,
            mappedBy = "userFavouriteExercise",
            cascade = CascadeType.REMOVE
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FavouriteExercises> favouriteExercise = new ArrayList<>();

    @OneToMany(
            targetEntity = ExercisesWithParameters.class,
            mappedBy = "exercises",
            cascade = CascadeType.REMOVE
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ExercisesWithParameters> exercisesWithParameters;

    public Exercises(String exerciseName, String description, Categories category, List<Muscles> musclesList, List<Muscles> secondaryMuscles, Equipment equipment) {
        this.exerciseName = exerciseName;
        this.description = description;
        this.category = category;
        this.muscles = musclesList;
        this.secondaryMuscles = secondaryMuscles;
        this.equipment = equipment;
    }
}
