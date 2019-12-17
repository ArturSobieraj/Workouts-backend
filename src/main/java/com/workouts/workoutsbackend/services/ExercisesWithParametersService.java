package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.ExercisesWithParameters;
import com.workouts.workoutsbackend.domain.dao.ExercisesWithParametersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisesWithParametersService {
    @Autowired
    private ExercisesWithParametersDao exercisesWithParametersDao;

    public ExercisesWithParameters saveExerciseForNewWorkout(ExercisesWithParameters exercisesWithParameters) {
        return exercisesWithParametersDao.save(exercisesWithParameters);
    }

    public List<ExercisesWithParameters> getExercisesForNewWorkout() {
        return exercisesWithParametersDao.getExercisesForNewWorkout();
    }

    public void deleteExercise(Long id) {
        exercisesWithParametersDao.deleteById(id);
    }

    public void deleteAllExercises() {
        exercisesWithParametersDao.deleteAll();
    }
}
