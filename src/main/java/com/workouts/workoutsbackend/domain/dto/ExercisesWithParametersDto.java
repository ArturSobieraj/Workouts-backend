package com.workouts.workoutsbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExercisesWithParametersDto {

    private Long id;
    private ExerciseDto exerciseDto;
    private String numberOfSeries;
    private String numberOfRepetitions;
    private String pauseTime;
}
