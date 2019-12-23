package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.FavouriteExercises;
import com.workouts.workoutsbackend.domain.dto.FavouriteExerciseDto;
import com.workouts.workoutsbackend.services.ExerciseService;
import com.workouts.workoutsbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavouriteExerciseMapper {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ExercisesMapper exercisesMapper;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ExerciseService exerciseService;

    public List<FavouriteExerciseDto> mapToFavouriteExercisesDtoList(List<FavouriteExercises> favouriteExercisesList) {
        return favouriteExercisesList.stream()
                .map(favouriteExercises -> new FavouriteExerciseDto(
                        favouriteExercises.getId(),
                        usersMapper.mapToUsersDto(favouriteExercises.getUser()),
                        exercisesMapper.mapToExerciseDto(favouriteExercises.getUserFavouriteExercise())
                ))
                .collect(Collectors.toList());
    }

    public FavouriteExercises mapToFavouriteExercises(FavouriteExerciseDto favouriteExerciseDto) {
        return new FavouriteExercises(usersService.getUser(favouriteExerciseDto.getUserDto().getMail()),
                exerciseService.getExerciseById(favouriteExerciseDto.getUserFavouriteExercise().getId()));
    }
}
