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
    private Long id;

    @NotNull
    @Column(name = "USER_LOGIN")
    private String email;

    @NotNull
    @Column(name = "USER_PASSWORD")
    private String password;

    @OneToMany(
            targetEntity = FavouriteExercises.class,
            mappedBy = "user",
            cascade = CascadeType.REMOVE
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FavouriteExercises> favouriteExercises = new ArrayList<>();

    @OneToMany(
            targetEntity = Workouts.class,
            mappedBy = "exercisingUser",
            cascade = CascadeType.REMOVE
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Workouts> workouts = new ArrayList<>();

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
