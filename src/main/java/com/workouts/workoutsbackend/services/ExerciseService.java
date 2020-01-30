package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.Exercises;
import com.workouts.workoutsbackend.domain.dao.ExercisesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseService {
    @Autowired
    private ExercisesDao exercisesDao;

    public Exercises saveExercise(Exercises exercise) {
        return exercisesDao.save(exercise);
    }

    public Exercises getExerciseByName(String exerciseName) {
        return exercisesDao.findByExerciseName(exerciseName).orElse(new Exercises());
    }

    public Exercises getExerciseById(Long id) {
        return exercisesDao.findById(id).orElse(new Exercises());
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
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
