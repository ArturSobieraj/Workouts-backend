package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.Equipment;
import com.workouts.workoutsbackend.domain.Exercises;
import com.workouts.workoutsbackend.domain.Muscles;
import com.workouts.workoutsbackend.domain.wgerDto.categories.CategoriesResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.equipment.EquipmentResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.exercises.ExerciseResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.muscles.MuscleResponseDto;
import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.EquipmentService;
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
    @Autowired
    private EquipmentService equipmentService;

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

    public List<Equipment> mapToEquipmentList(final List<EquipmentResponseDto> equipmentResponseDtoList) {
        return equipmentResponseDtoList.stream()
                .map(eRDto -> new Equipment(eRDto.getExternalId(), eRDto.getEquipmentName()))
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
                    List<Muscles> secondaryMusclesList = new ArrayList<>();
                    exerciseResponseDto.getMuscles().forEach(Integer -> {
                                Optional<Muscles> muscle = musclesService.findByExternalId(Integer);
                                muscle.ifPresent(musclesList::add);
                            });

                    exerciseResponseDto.getSecondaryMuscles().forEach(Integer -> {
                        Optional<Muscles> secondaryMuscle = musclesService.findByExternalId(Integer);
                        secondaryMuscle.ifPresent(secondaryMusclesList::add);
                    });
                    exercise.setMuscles(musclesList);
                    exercise.setSecondaryMuscles(secondaryMusclesList);

                    List<Equipment> equipmentList = new ArrayList<>();
                    exerciseResponseDto.getEquipment().forEach(Integer -> {
                        Optional<Equipment> equipment = equipmentService.getEquipmentByExternalId(Integer);
                        equipment.ifPresent(equipmentList::add);
                    });
                    if (equipmentList.size() != 0) {
                        exercise.setEquipment(equipmentList.get(0));
                    } else {
                        exercise.setEquipment(null);
                    }
                    return exercise;
                })
                .collect(Collectors.toList());
    }
}
