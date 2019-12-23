package com.workouts.workoutsbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FavouriteExerciseDto {

    private Long id;
    private UsersDto userDto;
    private ExerciseDto userFavouriteExercise;
}
