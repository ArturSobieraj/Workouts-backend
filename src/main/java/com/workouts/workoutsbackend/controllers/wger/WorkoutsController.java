package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.dto.WorkoutsDto;
import com.workouts.workoutsbackend.mappers.WorkoutsMapper;
import com.workouts.workoutsbackend.services.UsersService;
import com.workouts.workoutsbackend.services.WorkoutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "get")
    public List<WorkoutsDto> getUsersWorkouts(@RequestParam String userMail) {
        return workoutsMapper.mapToWorkoutsDtoList
                (workoutsService.getUsersWorkouts(usersService.getUser(userMail)));
    }

    @DeleteMapping(value = "delete")
    public void deleteWorkout(@RequestParam String workoutName) {
        workoutsService.deleteWorkoutByName(workoutName);
    }
}
