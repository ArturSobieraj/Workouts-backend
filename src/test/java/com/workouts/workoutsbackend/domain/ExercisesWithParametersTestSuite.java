package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.domain.dao.ExercisesWithParametersDao;
import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.ExerciseService;
import com.workouts.workoutsbackend.services.ExercisesWithParametersService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ExercisesWithParametersTestSuite {
    @Autowired
    private ExercisesWithParametersService exercisesWithParametersService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private CategoriesService categoriesService;

    @Test
    public void testSaveAndGetExerciseWithParameters() {
        //Given
        Categories category = new Categories();
        category.setExternalId(1);
        category.setCategoryName("test");
        categoriesService.saveCategory(category);
        Exercises exercise = new Exercises();
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);
        exerciseService.saveExercise(exercise);
        ExercisesWithParameters exercisesWithParameters = new ExercisesWithParameters();
        exercisesWithParameters.setExercises(exercise);
        exercisesWithParameters.setNumberOfRepetitions("10");
        exercisesWithParameters.setNumberOfSeries("5");
        exercisesWithParameters.setPauseTime("60");

        //When
        exercisesWithParametersService.saveExerciseForNewWorkout(exercisesWithParameters);
        List<ExercisesWithParameters> getExercises = exercisesWithParametersService.getExercisesForNewWorkout();

        //Then
        Assert.assertEquals(1, getExercises.size());

        //CleanUp
        exercisesWithParametersService.deleteAllExercises();
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
    }

    @After
    public void cleanUp() {
        exerciseService.deleteAllExercises();
        exercisesWithParametersService.deleteAllExercises();
        categoriesService.deleteAllCategories();
    }
}
