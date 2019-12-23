package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.*;
import com.workouts.workoutsbackend.domain.dto.wgerDto.categories.CategoriesResponseDto;
import com.workouts.workoutsbackend.domain.dto.wgerDto.exercises.ExerciseResponseDto;
import com.workouts.workoutsbackend.domain.dto.wgerDto.muscles.MuscleResponseDto;
import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.MusclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WgerMapper {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private MusclesService musclesService;

    public List<Categories> mapToCategoriesList(final List<CategoriesResponseDto> categoriesResponseDtoList) {
        return categoriesResponseDtoList.stream()
                .map(cRDto -> new Categories(cRDto.getExternalId(), cRDto.getCategoryName()))
                .collect(Collectors.toList());
    }

    public List<Muscles> mapToMuscleList(final List<MuscleResponseDto> muscleResponseDtoList) {
        return muscleResponseDtoList.stream()
                .map(mRDto -> new Muscles(mRDto.getExternalId(), mRDto.getMuscleName()))
                .collect(Collectors.toList());
    }

    public List<Exercises> mapToExercisesList(final List<ExerciseResponseDto> exerciseResponseDtoList) {
        return exerciseResponseDtoList.stream()
                .map(exerciseResponseDto -> {
                    Exercises exercise = new Exercises();
                    exercise.setExerciseName(exerciseResponseDto.getExerciseName());
                    exercise.setDescription(exerciseResponseDto.getDescription());

                    Optional<Categories> category = categoriesService.getCategoryByExternalId(exerciseResponseDto.getCategory());
                    category.ifPresent(exercise::setCategory);

                    List<Muscles> musclesList = new ArrayList<>();
                    exerciseResponseDto.getMuscles().forEach(Integer -> {
                                Optional<Muscles> muscle = musclesService.findByExternalId(Integer);
                                muscle.ifPresent(musclesList::add);
                            });
                    exercise.setMuscles(musclesList);
                    return exercise;
                })
                .collect(Collectors.toList());
    }
}
