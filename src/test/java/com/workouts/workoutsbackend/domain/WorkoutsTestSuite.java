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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkoutsTestSuite {
    @Autowired
    private WorkoutsService workoutsService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ExercisesWithParametersService exercisesWithParametersService;
    @Autowired
    private UsersService usersService;

    @Test
    public void testSaveAndGetWorkout() {
        //Given
        Categories category = new Categories();
        category.setCategoryName("test category");
        category.setExternalId(1);
        Exercises exercise = new Exercises();
        exercise.setExerciseName("test name");
        exercise.setDescription("test description");
        exercise.setCategory(category);
        ExercisesWithParameters exercisesWithParameters = new ExercisesWithParameters();
        exercisesWithParameters.setExercises(exercise);
        exercisesWithParameters.setNumberOfRepetitions("10");
        exercisesWithParameters.setNumberOfSeries("5");
        exercisesWithParameters.setPauseTime("60");
        List<ExercisesWithParameters> exercisesWithParametersList = new ArrayList<>();
        exercisesWithParametersList.add(exercisesWithParameters);
        Users user = new Users();
        user.setEmail("test mail");
        user.setPassword("test password");
        Workouts workout = new Workouts();
        workout.setWorkoutName("test name");
        workout.setExercisesWithParameters(exercisesWithParametersList);
        workout.setLocalDate(LocalDate.now());
        workout.setExercisingUser(user);

        //When
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        exercisesWithParametersService.saveExerciseForNewWorkout(exercisesWithParameters);
        usersService.saveUser(user);
        workoutsService.saveWorkout(workout);

        List<Workouts> getUserWorkouts = workoutsService.getUsersWorkouts(user);

        //Then
        Assert.assertEquals(1, getUserWorkouts.size());

        //CleanUp
        workoutsService.deleteAllWorkouts();
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        usersService.deleteAllUsers();
    }

    @Test
    public void testWorkoutsRelations() {
        //Given
        Categories category = new Categories();
        category.setCategoryName("test category");
        category.setExternalId(1);
        Exercises exercise = new Exercises();
        exercise.setExerciseName("test name");
        exercise.setDescription("test description");
        exercise.setCategory(category);
        ExercisesWithParameters exercisesWithParameters = new ExercisesWithParameters();
        exercisesWithParameters.setExercises(exercise);
        exercisesWithParameters.setNumberOfRepetitions("10");
        exercisesWithParameters.setNumberOfSeries("5");
        exercisesWithParameters.setPauseTime("60");
        List<ExercisesWithParameters> exercisesWithParametersList = new ArrayList<>();
        exercisesWithParametersList.add(exercisesWithParameters);
        Users user = new Users();
        user.setEmail("test mail");
        user.setPassword("test password");
        Workouts workout = new Workouts();
        workout.setWorkoutName("test name");
        workout.setExercisesWithParameters(exercisesWithParametersList);
        workout.setLocalDate(LocalDate.now());
        workout.setExercisingUser(user);

        //When
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        exercisesWithParametersService.saveExerciseForNewWorkout(exercisesWithParameters);
        usersService.saveUser(user);
        workoutsService.saveWorkout(workout);
        workoutsService.deleteWorkoutByName(workout.getWorkoutName());

        List<Workouts> getWorkout = workoutsService.getUsersWorkouts(user);
        List<ExercisesWithParameters> getExerciseWithParametersByWorkout = exercisesWithParametersService.getExercisesByWorkout(workout.getId());
        Users users = usersService.getUser("test mail");

        //Then
        Assert.assertEquals(0, getWorkout.size());
        Assert.assertEquals(0, getExerciseWithParametersByWorkout.size());
        Assert.assertNotNull(users);

        //CleanUp
        workoutsService.deleteAllWorkouts();
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        usersService.deleteAllUsers();
    }

    @After
    public void cleanUp() {
        exerciseService.deleteAllExercises();
        usersService.deleteAllUsers();
        categoriesService.deleteAllCategories();
    }
}
