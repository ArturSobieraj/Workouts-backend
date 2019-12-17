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
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "WORKOUT_NAME")
    private String workoutName;

    @NotNull
    @OneToMany(
            targetEntity = ExercisesWithParameters.class,
            mappedBy = "workouts",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<ExercisesWithParameters> exercisesWithParameters = new ArrayList<>();

    @NotNull
    @Column(name = "TRAINING_DATE")
    private LocalDate localDate;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Users exercisingUser;
}
