package com.workouts.workoutsbackend.wger;

import com.workouts.workoutsbackend.clients.WgerClient;
import com.workouts.workoutsbackend.controllers.wger.WgerController;
import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.Equipment;
import com.workouts.workoutsbackend.domain.Exercises;
import com.workouts.workoutsbackend.domain.Muscles;
import com.workouts.workoutsbackend.domain.wgerDto.categories.CategoriesResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.equipment.EquipmentResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.exercises.ExerciseResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.muscles.MuscleResponseDto;
import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.EquipmentService;
import com.workouts.workoutsbackend.services.ExerciseService;
import com.workouts.workoutsbackend.services.MusclesService;
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
public class WgerDataSaveTestSuite {
    @Autowired
    private WgerController wgerController;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private WgerClient wgerClient;
    @Autowired
    private MusclesService musclesService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ExerciseService exerciseService;

    @Test
    public void testSaveWgerCategories() {
        //Given
        //When
        List<CategoriesResponseDto> categoriesResponseDtoList = wgerClient.getWgerCategories();
        wgerController.getCategories();
        List<Categories> categoriesList = categoriesService.getAllCategories();

        //Then
        Assert.assertEquals(categoriesResponseDtoList.size(), categoriesList.size());

        //CleanUp
        categoriesService.deleteAllCategories();
    }

    @Test
    public void testSaveWgerMuscles() {
        //Given
        //When
        List<MuscleResponseDto> muscleResponseDtoList = wgerClient.getWgerMuscles();
        wgerController.getMuscles();
        List<Muscles> musclesList = musclesService.getAllMuscles();

        //Then
        Assert.assertEquals(muscleResponseDtoList.size(), musclesList.size());

        //CleanUp
        musclesService.deleteAllMuscles();
    }

    @Test
    public void testSaveWgerEquipment() {
        //Given
        //When
        List<EquipmentResponseDto> equipmentResponseDtoList = wgerClient.getWgerEquipment();
        wgerController.getEquipment();
        List<Equipment> equipmentList = equipmentService.getAllEquipment();

        //Then
        Assert.assertEquals(equipmentResponseDtoList.size(), equipmentList.size());

        //CleanUp
        equipmentService.deleteAllEquipment();
    }

    @Test
    public void testSaveWgerExercises() {
        //Given
        //When
        List<ExerciseResponseDto> exerciseResponseDtoList = wgerClient.getWgerExercises();
        wgerController.getEquipment();
        wgerController.getMuscles();
        wgerController.getCategories();
        wgerController.getExercises();
        List<Exercises> exercisesList = exerciseService.getAllExercises();

        //Then
        Assert.assertEquals(exerciseResponseDtoList.size(), exercisesList.size());

        //CleanUp
        categoriesService.deleteAllCategories();
        musclesService.deleteAllMuscles();
        equipmentService.deleteAllEquipment();
        exerciseService.deleteAllExercises();
    }

    @After
    public void cleanUp() {
        categoriesService.deleteAllCategories();
        musclesService.deleteAllMuscles();
        equipmentService.deleteAllEquipment();
        exerciseService.deleteAllExercises();
    }
}
