package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.Workouts;
import com.workouts.workoutsbackend.domain.dto.WorkoutsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkoutsMapper {
    @Autowired
    private ExerciseWithParametersMapper exerciseWithParametersMapper;
    @Autowired
    private UsersMapper usersMapper;

    public List<WorkoutsDto> mapToWorkoutsDtoList(final List<Workouts> workoutsList) {
        return workoutsList.stream()
                .map(workouts ->
                        new WorkoutsDto(workouts.getId(),
                                workouts.getWorkoutName(),
                                exerciseWithParametersMapper.exercisesWithParametersDtoList
                                        (workouts.getExercisesWithParameters()),
                                workouts.getLocalDate(),
                                usersMapper.mapToUsersDto(workouts.getExercisingUser())))
                .collect(Collectors.toList());
    }
}
