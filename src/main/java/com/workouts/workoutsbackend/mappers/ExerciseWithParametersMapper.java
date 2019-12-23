package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.ExercisesWithParameters;
import com.workouts.workoutsbackend.domain.dto.ExercisesWithParametersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExerciseWithParametersMapper {
    @Autowired
    private ExercisesMapper exercisesMapper;

    public List<ExercisesWithParametersDto> exercisesWithParametersDtoList
            (final List<ExercisesWithParameters> exercisesWithParametersList) {
        return exercisesWithParametersList.stream()
                .map(exercisesWithParameters -> new ExercisesWithParametersDto(
                        exercisesWithParameters.getId(),
                        exercisesMapper.mapToExerciseDto(exercisesWithParameters.getExercises()),
                        exercisesWithParameters.getNumberOfSeries(),
                        exercisesWithParameters.getNumberOfRepetitions(),
                        exercisesWithParameters.getPauseTime()
                ))
                .collect(Collectors.toList());
    }
}
