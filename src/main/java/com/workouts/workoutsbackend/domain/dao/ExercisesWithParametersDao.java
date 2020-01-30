package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.ExercisesWithParameters;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ExercisesWithParametersDao extends CrudRepository<ExercisesWithParameters, Long> {

    @Override
    ExercisesWithParameters save(ExercisesWithParameters exercisesWithParameters);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();

    void deleteByUserName(String userName);

    List<ExercisesWithParameters> getExercisesByUserName(String userName);

    @Query
    List<ExercisesWithParameters> getExercisesForNewWorkout();

    @Query
    List<ExercisesWithParameters> getExercisesByWorkout(@Param("workout_id") Long workout_id);
}
