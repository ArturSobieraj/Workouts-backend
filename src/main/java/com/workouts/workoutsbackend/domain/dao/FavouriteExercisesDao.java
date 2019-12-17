package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.FavouriteExercises;
import com.workouts.workoutsbackend.domain.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FavouriteExercisesDao extends CrudRepository<FavouriteExercises, Long> {

    @Override
    FavouriteExercises save(FavouriteExercises favouriteExercises);

    @Override
    void deleteAll();

    @Query(nativeQuery = true)
    void deleteUsersFavouriteExercise(@Param("USER_ID") Long user_id, @Param("EXERCISE_ID") Long exercise_id);

    List<FavouriteExercises> getFavouritesByUser(Users user);
}
