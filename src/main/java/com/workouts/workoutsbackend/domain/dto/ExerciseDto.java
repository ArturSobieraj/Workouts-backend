package com.workouts.workoutsbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExerciseDto {

    private Long id;
    private String exerciseName;
    private String description;
    private String category;
    private List<String> muscles;
}
