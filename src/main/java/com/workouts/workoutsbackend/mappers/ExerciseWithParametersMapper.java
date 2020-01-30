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

    public List<ExercisesWithParametersDto> mapToExercisesWithParametersDtoList
            (List<ExercisesWithParameters> exercisesWithParametersList) {
        return exercisesWithParametersList.stream()
                .map(exercisesWithParameters -> new ExercisesWithParametersDto(
                        exercisesWithParameters.getId(),
                        exercisesMapper.mapToExerciseDto(exercisesWithParameters.getExercises()),
                        exercisesWithParameters.getNumberOfSeries(),
                        exercisesWithParameters.getNumberOfRepetitions(),
                        exercisesWithParameters.getPauseTime(),
                        exercisesWithParameters.getUserName()
                ))
                .collect(Collectors.toList());
    }

    public ExercisesWithParameters mapToExerciseWithParameters(ExercisesWithParametersDto exercisesWithParametersDto) {
        return new ExercisesWithParameters(exercisesMapper.mapToExercise(exercisesWithParametersDto.getExerciseDto()),
                exercisesWithParametersDto.getNumberOfSeries(),
                exercisesWithParametersDto.getNumberOfRepetitions(),
                exercisesWithParametersDto.getPauseTime(),
                exercisesWithParametersDto.getUserName());
    }

    public List<ExercisesWithParameters> removeUserName(List<ExercisesWithParameters> exercisesWithParametersList) {
        return exercisesWithParametersList.stream()
                .map(exerciseWithParameters -> new ExercisesWithParameters(
                                exerciseWithParameters.getExercises(),
                                exerciseWithParameters.getNumberOfSeries(),
                                exerciseWithParameters.getNumberOfRepetitions(),
                                exerciseWithParameters.getPauseTime()))
                .collect(Collectors.toList());
    }

    public ExercisesWithParametersDto mapToExercisesWithParametersDto(ExercisesWithParameters exercisesWithParameters) {
        return new ExercisesWithParametersDto(exercisesWithParameters.getId(),
                exercisesMapper.mapToExerciseDto(exercisesWithParameters.getExercises()),
                exercisesWithParameters.getNumberOfSeries(),
                exercisesWithParameters.getNumberOfRepetitions(),
                exercisesWithParameters.getPauseTime(),
                exercisesWithParameters.getUserName());
    }

    public ExercisesWithParameters mapToExerciseWithParametersWithId(ExercisesWithParametersDto exercisesWithParametersDto) {
        return new ExercisesWithParameters(exercisesWithParametersDto.getId(),
                exercisesMapper.mapToExercise(exercisesWithParametersDto.getExerciseDto()),
                exercisesWithParametersDto.getNumberOfSeries(),
                exercisesWithParametersDto.getNumberOfRepetitions(),
                exercisesWithParametersDto.getPauseTime(),
                exercisesWithParametersDto.getUserName());
    }
}
