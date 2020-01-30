package com.workouts.workoutsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WORKOUTS")
public class Workouts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "WORKOUT_NAME")
    private String workoutName;

    @OneToMany(
            targetEntity = ExercisesWithParameters.class,
            mappedBy = "workouts",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<ExercisesWithParameters> exercisesWithParameters = new ArrayList<>();

    @NotNull
    @Column(name = "TRAINING_DATE")
    private LocalDate localDate;

    @NotNull
    @Column(name = "USER_NAME")
    private String exercisingUser;

    private Workouts(String workoutName, List<ExercisesWithParameters> exercisesWithParameters, LocalDate trainingDate, String exercisingUser) {
        this.workoutName = workoutName;
        this.exercisesWithParameters = exercisesWithParameters;
        this.localDate = trainingDate;
        this.exercisingUser = exercisingUser;
    }

    public static class WorkoutBuilder {
        private String workoutName;
        private List<ExercisesWithParameters> workoutsExercises = new ArrayList<>();
        private LocalDate workoutDate;
        private String exercisingUser;

        public WorkoutBuilder workoutName(String workoutName) {
            this.workoutName = workoutName;
            return this;
        }

        public WorkoutBuilder workoutExercises(List<ExercisesWithParameters> exercisesWithParameters) {
            this.workoutsExercises = exercisesWithParameters;
            return this;
        }

        public WorkoutBuilder workoutDate(LocalDate workoutDate) {
            this.workoutDate = workoutDate;
            return this;
        }

        public WorkoutBuilder exercisingUser(String exercisingUser) {
            this.exercisingUser = exercisingUser;
            return this;
        }

        public Workouts build() {
            return new Workouts(workoutName, workoutsExercises, workoutDate, exercisingUser);
        }
    }
}
