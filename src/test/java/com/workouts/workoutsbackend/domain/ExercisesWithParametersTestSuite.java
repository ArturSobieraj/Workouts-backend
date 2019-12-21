package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.domain.dao.ExercisesWithParametersDao;
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
import java.util.Optional;

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
    @Autowired
    private WorkoutsService workoutsService;
    @Autowired
    private UsersService usersService;

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
        workoutsService.deleteAllWorkouts();
        usersService.deleteAllUsers();
    }

    @Test
    public void testExercisesWithParametersRelations() {
        //Given
        Categories category = new Categories();
        category.setExternalId(1);
        category.setCategoryName("test");
        Exercises exercise = new Exercises();
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);
        ExercisesWithParameters exercisesWithParameters = new ExercisesWithParameters();
        exercisesWithParameters.setExercises(exercise);
        exercisesWithParameters.setNumberOfRepetitions("10");
        exercisesWithParameters.setNumberOfSeries("5");
        exercisesWithParameters.setPauseTime("60");
        Users user = new Users();
        user.setEmail("test mail");
        user.setPassword("test password");
        Workouts workout = new Workouts();
        workout.setWorkoutName("test name");
        workout.setLocalDate(LocalDate.now());
        workout.setExercisingUser(user);
        exercisesWithParameters.setWorkouts(workout);

        //When
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        usersService.saveUser(user);
        exercisesWithParametersService.saveExerciseForNewWorkout(exercisesWithParameters);
        workoutsService.saveWorkout(workout);
        exercisesWithParametersService.deleteExercise(exercisesWithParameters.getId());

        List<Exercises> getAllExercises = exerciseService.getAllExercises();
        Optional<Categories> getCategory = categoriesService.getCategoryByName(category.getCategoryName());
        Users users = usersService.getUser(user.getEmail());
        List<Workouts> getWorkouts = workoutsService.getUsersWorkouts(user);

        //Then
        Assert.assertEquals(1, getAllExercises.size());
        Assert.assertEquals(1, getWorkouts.size());
        Assert.assertNotNull(users);
        Assert.assertTrue(getCategory.isPresent());

        //CleanUp
        exercisesWithParametersService.deleteAllExercises();
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        workoutsService.deleteAllWorkouts();
        usersService.deleteAllUsers();
    }

    @Test
    public void testNamedQueries() {
        //Given
        Categories category = new Categories();
        category.setExternalId(1);
        category.setCategoryName("test");
        Exercises exercise = new Exercises();
        exercise.setExerciseName("test");
        exercise.setDescription("test description");
        exercise.setCategory(category);
        ExercisesWithParameters exercisesWithParameters = new ExercisesWithParameters();
        ExercisesWithParameters exercisesWithParameters2 = new ExercisesWithParameters();
        exercisesWithParameters.setExercises(exercise);
        exercisesWithParameters2.setExercises(exercise);
        exercisesWithParameters.setNumberOfRepetitions("10");
        exercisesWithParameters2.setNumberOfRepetitions("10");
        exercisesWithParameters.setNumberOfSeries("5");
        exercisesWithParameters2.setNumberOfSeries("5");
        exercisesWithParameters.setPauseTime("60");
        exercisesWithParameters2.setPauseTime("60");
        Users user = new Users();
        user.setEmail("test mail");
        user.setPassword("test password");
        Workouts workout = new Workouts();
        workout.setWorkoutName("test name");
        workout.setLocalDate(LocalDate.now());
        workout.setExercisingUser(user);
        exercisesWithParameters.setWorkouts(workout);

        //When
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        usersService.saveUser(user);
        exercisesWithParametersService.saveExerciseForNewWorkout(exercisesWithParameters);
        exercisesWithParametersService.saveExerciseForNewWorkout(exercisesWithParameters2);
        workoutsService.saveWorkout(workout);

        List<ExercisesWithParameters> getExercisesWithNoWorkout = exercisesWithParametersService.getExercisesForNewWorkout();
        List<ExercisesWithParameters> getExercisesByWorkout = exercisesWithParametersService.getExercisesByWorkout(workout.getId());

        //Then
        Assert.assertEquals(1, getExercisesWithNoWorkout.size());
        Assert.assertEquals(1, getExercisesWithNoWorkout.size());

        //CleanUp
        exercisesWithParametersService.deleteAllExercises();
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        workoutsService.deleteAllWorkouts();
        usersService.deleteAllUsers();
    }

    @After
    public void cleanUp() {
        exercisesWithParametersService.deleteAllExercises();
        exerciseService.deleteAllExercises();
        categoriesService.deleteAllCategories();
        workoutsService.deleteAllWorkouts();
        usersService.deleteAllUsers();
    }
}
