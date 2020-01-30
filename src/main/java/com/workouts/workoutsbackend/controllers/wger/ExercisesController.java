package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.dto.ExerciseDto;
import com.workouts.workoutsbackend.facade.wgerFacade.WgerFacade;
import com.workouts.workoutsbackend.mappers.ExercisesMapper;
import com.workouts.workoutsbackend.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/exercises/")
public class ExercisesController {
    @Autowired
    private ExercisesMapper exercisesMapper;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private WgerFacade wgerFacade;

    @GetMapping(value = "all")
    public List<ExerciseDto> getAllExercises() {
        if (exerciseService.getExercisesCount() == 0) {
            wgerFacade.fetchAllData();
        }
        return exercisesMapper.mapToExercisesDtoList(exerciseService.getAllExercises());
    }

    @GetMapping(value = "byName")
    public ExerciseDto getExerciseByName(@RequestParam String name) {
        if (exerciseService.getExercisesCount() == 0) {
            wgerFacade.fetchAllData();
        }
        try {
            return exercisesMapper.mapToExerciseDto(exerciseService.getExerciseByName(name));
        } catch (NullPointerException e) {
            return new ExerciseDto();
        }
    }
}
