package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.ExercisesWithParameters;
import com.workouts.workoutsbackend.domain.Workouts;
import com.workouts.workoutsbackend.domain.dto.WorkoutsDto;
import com.workouts.workoutsbackend.mappers.ExerciseWithParametersMapper;
import com.workouts.workoutsbackend.mappers.WorkoutsMapper;
import com.workouts.workoutsbackend.services.ExercisesWithParametersService;
import com.workouts.workoutsbackend.services.UsersService;
import com.workouts.workoutsbackend.services.WorkoutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/workouts/")
public class WorkoutsController {

    @Autowired
    private WorkoutsService workoutsService;
    @Autowired
    private WorkoutsMapper workoutsMapper;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ExerciseWithParametersMapper exerciseWithParametersMapper;
    @Autowired
    private ExercisesWithParametersService exercisesWithParametersService;

    @GetMapping(value = "get")
    public List<WorkoutsDto> getUsersWorkouts(@RequestParam String userMail) {
        return workoutsMapper.mapToWorkoutsDtoList
                (workoutsService.getUsersWorkouts(userMail));
    }

    @GetMapping(value = "getById")
    public WorkoutsDto getWorkoutById(@RequestParam Long workoutId) {
        return workoutsMapper.mapToWorkoutsDto(workoutsService.getById(workoutId));
    }

    @DeleteMapping(value = "delete")
    public void deleteWorkout(@RequestParam String workoutName) {
        workoutsService.deleteWorkoutByName(workoutName);
    }

    @PostMapping(value = "add")
    public void addNewWorkout(@RequestParam String workoutName, @RequestBody LocalDate trainingDate, @RequestParam String userName) {
        List<ExercisesWithParameters> exercisesForWorkout = exerciseWithParametersMapper.removeUserName(exercisesWithParametersService.getUsersExercises(userName));
        exercisesWithParametersService.deleteUsersExercise(userName);
        Workouts newWorkout = new Workouts.WorkoutBuilder()
                .exercisingUser(userName)
                .workoutName(workoutName)
                .workoutDate(trainingDate)
                .workoutExercises(exercisesForWorkout)
                .build();
        for (ExercisesWithParameters exercises : exercisesForWorkout) {
            exercises.setWorkouts(newWorkout);
            exercisesWithParametersService.saveExerciseForNewWorkout(exercises);
        }
        workoutsService.saveWorkout(newWorkout);
    }
}
