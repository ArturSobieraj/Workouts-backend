package com.workouts.workoutsbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExercisesWithParametersDto {

    private Long id;
    private ExerciseDto exerciseDto;
    private String numberOfSeries;
    private String numberOfRepetitions;
    private String pauseTime;
    private String userName;
}
