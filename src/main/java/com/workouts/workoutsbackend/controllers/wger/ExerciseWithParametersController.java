package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.dto.ExercisesWithParametersDto;
import com.workouts.workoutsbackend.mappers.ExerciseWithParametersMapper;
import com.workouts.workoutsbackend.services.ExercisesWithParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/exwithparams/")
public class ExerciseWithParametersController {

    @Autowired
    private ExercisesWithParametersService exercisesWithParametersService;
    @Autowired
    private ExerciseWithParametersMapper exerciseWithParametersMapper;

    @GetMapping(value = "getallbyuser")
    public List<ExercisesWithParametersDto> getWorkoutExercises(@RequestParam String userName) {
        return exerciseWithParametersMapper.mapToExercisesWithParametersDtoList(exercisesWithParametersService.getUsersExercises(userName));
    }

    @PostMapping(value = "add")
    public void addNewExercise(@Valid @RequestBody ExercisesWithParametersDto exercisesWithParametersDto) {
        exercisesWithParametersService.saveExerciseForNewWorkout(exerciseWithParametersMapper.mapToExerciseWithParameters(exercisesWithParametersDto));
    }

    @PutMapping(value = "edit")
    public void editExercise(@Valid @RequestBody ExercisesWithParametersDto exercisesWithParametersDto) {
        exercisesWithParametersService.saveExerciseForNewWorkout(exerciseWithParametersMapper.mapToExerciseWithParametersWithId(exercisesWithParametersDto));
    }

    @DeleteMapping(value = "delete")
    public void deleteExercise(@RequestParam Long exerciseId) {
        exercisesWithParametersService.deleteExercise(exerciseId);
    }
}
