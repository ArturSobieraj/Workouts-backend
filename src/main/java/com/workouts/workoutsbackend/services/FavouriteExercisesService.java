package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.FavouriteExercises;
import com.workouts.workoutsbackend.domain.Users;
import com.workouts.workoutsbackend.domain.dao.FavouriteExercisesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteExercisesService {
    @Autowired
    private FavouriteExercisesDao favouriteExercisesDao;

    public FavouriteExercises saveNewFavouriteExercise(FavouriteExercises favouriteExercises) {
        return favouriteExercisesDao.save(favouriteExercises);
    }

    public void deleteAllFavourites() {
        favouriteExercisesDao.deleteAll();
    }

    public void deleteUserFavouriteExercise(Long id) {
        favouriteExercisesDao.deleteById(id);
    }

    public List<FavouriteExercises> getUserFavourites(Users user) {
        return favouriteExercisesDao.getFavouritesByUser(user);
    }
}
