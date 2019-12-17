package com.workouts.workoutsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(
        name = "ExercisesWithParameters.getExercisesForNewWorkout",
        query = "FROM ExercisesWithParameters WHERE workouts = null"
)
@Entity
@Table(name = "EXERCISES_WITH_PARAMETERS")
public class ExercisesWithParameters {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "EXERCISE_ID")
    private Exercises exercises;

    @NotNull
    @Column(name = "NUMBER_OF_SERIES")
    private String numberOfSeries;

    @NotNull
    @Column(name = "NUMBER_OF_REPETITIONS")
    private String numberOfRepetitions;

    @NotNull
    @Column(name = "PAUSE_TIME")
    private String pauseTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WORKOUT_ID")
    private Workouts workouts;
}
