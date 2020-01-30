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
@NamedQueries({
        @NamedQuery(
                name = "ExercisesWithParameters.getExercisesForNewWorkout",
                query = "FROM ExercisesWithParameters WHERE workouts = null"
        ),
        @NamedQuery(
                name = "ExercisesWithParameters.getExercisesByWorkout",
                query = "FROM ExercisesWithParameters WHERE workout_id = :workout_id"
        )
})
@Entity
@Table(name = "EXERCISES_WITH_PARAMETERS")
public class ExercisesWithParameters {

    @Id
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

    @Column(name = "USER_NAME")
    private String userName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WORKOUT_ID")
    private Workouts workouts;

    public ExercisesWithParameters(Exercises exercises, String numberOfSeries, String numberOfRepetitions, String pauseTime, String userName) {
        this.exercises = exercises;
        this.numberOfSeries = numberOfSeries;
        this.numberOfRepetitions = numberOfRepetitions;
        this.pauseTime = pauseTime;
        this.userName = userName;
    }

    public ExercisesWithParameters(Long id, Exercises exercises, String numberOfSeries, String numberOfRepetitions, String pauseTime, String userName) {
        this.id = id;
        this.exercises = exercises;
        this.numberOfSeries = numberOfSeries;
        this.numberOfRepetitions = numberOfRepetitions;
        this.pauseTime = pauseTime;
        this.userName = userName;
    }

    public ExercisesWithParameters(Exercises exercises, String numberOfSeries, String numberOfRepetitions, String pauseTime) {
        this.exercises = exercises;
        this.numberOfSeries = numberOfSeries;
        this.numberOfRepetitions = numberOfRepetitions;
        this.pauseTime = pauseTime;
    }
}
