package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.Exercises;
import com.workouts.workoutsbackend.domain.dto.ExerciseDto;
import com.workouts.workoutsbackend.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExercisesMapper {
    @Autowired
    private CategoriesMapper categoriesMapper;
    @Autowired
    private MusclesMapper musclesMapper;
    @Autowired
    private CategoriesService categoriesService;

    public ExerciseDto mapToExerciseDto(Exercises exercises) {
        return new ExerciseDto(exercises.getId(),
                exercises.getExerciseName(),
                exercises.getDescription(),
                categoriesMapper.mapToCategoriesName(exercises.getCategory()),
                musclesMapper.mapToMusclesNamesList(exercises.getMuscles()));

    }

    public List<ExerciseDto> mapToExercisesDtoList(List<Exercises> exercisesList) {
        List<ExerciseDto> exerciseDtoList = exercisesList.stream()
                .map(exercises -> new ExerciseDto(exercises.getId(),
                        exercises.getExerciseName(),
                        exercises.getDescription(),
                        categoriesMapper.mapToCategoriesName(exercises.getCategory()),
                        musclesMapper.mapToMusclesNamesList(exercises.getMuscles())))
                .collect(Collectors.toList());
        return exerciseDtoList;
    }

    public Exercises mapToExercise(ExerciseDto exerciseDto) {
        return new Exercises(exerciseDto.getId(),
                exerciseDto.getExerciseName(),
                exerciseDto.getDescription(),
                categoriesService.getCategoryByName(exerciseDto.getCategory()),
                musclesMapper.mapToMusclesList(exerciseDto.getMuscles())
                );
    }
}
