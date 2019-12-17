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
@NamedNativeQuery(
        name = "FavouriteExercises.deleteUsersFavouriteExercise",
        query = "DELETE FROM FAVOURITE_EXERCISES WHERE USER_ID = :USER_ID AND EXERCISE_ID = :EXERCISE_ID",
        resultClass = FavouriteExercises.class
)
@Entity
@Table(name = "FAVOURITE_EXERCISES")
public class FavouriteExercises {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Users user;

    @NotNull
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "EXERCISE_ID")
    private Exercises exercise;

}
