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
@Table(name = "CATEGORIES")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "EXTERNAL_ID", unique = true)
    private Integer externalId;

    @NotNull
    @Column(name = "CATEGORY_NAME", unique = true)
    private String categoryName;

    @OneToMany(
            targetEntity = Exercises.class,
            mappedBy = "category",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Exercises> exercises = new ArrayList<>();

    public Categories(Integer externalId, String categoryName) {
        this.externalId = externalId;
        this.categoryName = categoryName;
    }
}
