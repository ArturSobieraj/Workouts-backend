package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.FavouriteExercises;
import com.workouts.workoutsbackend.domain.dto.FavouriteExerciseDto;
import com.workouts.workoutsbackend.mappers.FavouriteExerciseMapper;
import com.workouts.workoutsbackend.services.ExerciseService;
import com.workouts.workoutsbackend.services.FavouriteExercisesService;
import com.workouts.workoutsbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/favExercises/")
public class FavouriteExercisesController {
    @Autowired
    private FavouriteExercisesService favouriteExercisesService;
    @Autowired
    private FavouriteExerciseMapper favouriteExerciseMapper;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping(value = "get")
    public List<FavouriteExerciseDto> getAllFavouriteExercises(@RequestParam String userMail) {
        return favouriteExerciseMapper.mapToFavouriteExercisesDtoList(favouriteExercisesService.getUserFavourites(usersService.getUser(userMail)));
    }

    @PostMapping(value = "add")
    public void addFavouriteExercise(@RequestParam String userMail, @RequestParam Long exerciseId) {
        FavouriteExercises addedExercise = new FavouriteExercises(usersService.getUser(userMail), exerciseService.getExerciseById(exerciseId));
        favouriteExercisesService.saveNewFavouriteExercise(addedExercise);
    }

    @DeleteMapping(value = "delete")
    public void deleteFavouriteExercise(@RequestParam Long id) {
        favouriteExercisesService.deleteUserFavouriteExercise(id);
    }
}
