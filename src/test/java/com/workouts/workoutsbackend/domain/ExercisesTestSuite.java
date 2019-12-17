package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.ExerciseService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ExercisesTestSuite {
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private CategoriesService categoriesService;

    @Test
    public void testSaveAndGetExercise() {
        //Given
        Exercises exercise = new Exercises();
        Categories category = new Categories();
        category.setExternalId(1);
        category.setCategoryName("test");
        categoriesService.saveCategory(category);
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);

        //When
        exerciseService.saveExercise(exercise);
        Optional<Exercises> getByName = exerciseService.getExerciseByName("test");

        //Then
        Assert.assertTrue(getByName.isPresent());

        //CleanUp
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
    }

    @After
    public void cleanUp() {
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
    }
}
