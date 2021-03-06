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

import java.util.List;

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
        category.setCategoryName("test category");
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);

        //When
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        Exercises getByName = exerciseService.getExerciseByName("test");
        List<Exercises> getAllExercises = exerciseService.getAllExercises();
        List<Exercises> getExercisesByCategory = exerciseService.getExercisesByCategory(category);

        //Then
        Assert.assertEquals("test", getByName.getExerciseName());
        Assert.assertEquals(1, getAllExercises.size());
        Assert.assertEquals(1, getExercisesByCategory.size());

        //CleanUp
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
    }

    @Test
    public void testExercisesRelations() {
        //Given
        Exercises exercise = new Exercises();
        Categories category = new Categories();
        category.setExternalId(1);
        category.setCategoryName("test category");
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);

        //When
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        exerciseService.deleteExerciseById(exercise.getId());

        Categories getCategory = categoriesService.getCategoryByName(category.getCategoryName());

        //Then
        Assert.assertEquals("test category", getCategory.getCategoryName());

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
