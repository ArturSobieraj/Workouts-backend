package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.Exercises;
import com.workouts.workoutsbackend.domain.dao.ExercisesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    @Autowired
    private ExercisesDao exercisesDao;

    public Exercises saveExercise(Exercises exercise) {
        return exercisesDao.save(exercise);
    }

    public Optional<Exercises> getExerciseByName(String exerciseName) {
        return exercisesDao.findByExerciseName(exerciseName);
    }

    public List<Exercises> getAllExercises() {
        return exercisesDao.findAll();
    }

    public List<Exercises> getExercisesByCategory(Categories category) {
        return exercisesDao.findByCategory(category);
    }

    public void deleteExerciseById(Long id) {
        exercisesDao.deleteById(id);
    }

    public void deleteAllExercises() {
        exercisesDao.deleteAll();
    }

    public long getExercisesCount() {
        return exercisesDao.count();
    }
}
