package com.workouts.workoutsbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkoutsDto {

    private Long id;
    private String workoutName;
    private List<ExercisesWithParametersDto> exercisesWithSeriesRepetitionsBreaks;
    private LocalDate trainingDate;
    private String userName;
}
