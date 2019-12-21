package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.services.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ExercisesTestSuite {
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private EquipmentService equipmentService;

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
        Optional<Exercises> getByName = exerciseService.getExerciseByName("test");
        List<Exercises> getAllExercises = exerciseService.getAllExercises();
        List<Exercises> getExercisesByCategory = exerciseService.getExercisesByCategory(category);

        //Then
        Assert.assertTrue(getByName.isPresent());
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
        Equipment equipment = new Equipment();
        category.setExternalId(1);
        category.setCategoryName("test category");
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);
        equipment.setExternalId(1);
        equipment.setEquipmentName("test");
        exercise.setEquipment(equipment);

        //When
        equipmentService.saveEquipment(equipment);
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        exerciseService.deleteExerciseById(exercise.getId());

        Optional<Categories> getCategory = categoriesService.getCategoryByName(category.getCategoryName());
        Optional<Equipment> getEquipment = equipmentService.getEquipmentByExternalId(equipment.getExternalId());

        //Then
        Assert.assertTrue(getCategory.isPresent());
        Assert.assertTrue(getEquipment.isPresent());

        //CleanUp
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        equipmentService.deleteAllEquipment();
    }

    @After
    public void cleanUp() {
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        equipmentService.deleteAllEquipment();
    }
}
