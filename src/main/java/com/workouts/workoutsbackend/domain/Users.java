package com.workouts.workoutsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "USER_LOGIN")
    private String login;

    @NotNull
    @Column(name = "USER_PASSWORD")
    private String password;

    @OneToMany(
            targetEntity = FavouriteExercises.class,
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "FAVOURITE_EXERCISES")
    private List<FavouriteExercises> favouriteExercises = new ArrayList<>();
}
