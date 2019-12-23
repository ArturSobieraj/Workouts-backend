package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.Exercises;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ExercisesDao extends CrudRepository<Exercises, Long> {

    @Override
    Exercises save(Exercises exercises);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long id);

    Exercises findByExerciseName(String exerciseName);

    @Override
    Optional<Exercises> findById(Long id);

    @Override
    List<Exercises> findAll();

    List<Exercises> findByCategory(Categories category);

    @Override
    long count();
}
