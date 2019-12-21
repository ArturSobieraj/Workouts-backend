package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.ExerciseService;
import com.workouts.workoutsbackend.services.FavouriteExercisesService;
import com.workouts.workoutsbackend.services.UsersService;
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
public class FavouriteExercisesTestSuite {
    @Autowired
    private FavouriteExercisesService favouriteExercisesService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ExerciseService exerciseService;

    @Test
    public void testQuery() {
        Users user = new Users();
        user.setEmail("test mail");
        user.setPassword("test password");
        Categories category = new Categories();
        category.setCategoryName("test category");
        category.setExternalId(1);
        Exercises exercise = new Exercises();
        Exercises exercise2 = new Exercises();
        exercise.setExerciseName("test name");
        exercise2.setExerciseName("test name 2");
        exercise.setDescription("test description");
        exercise2.setDescription("test description2");
        exercise.setCategory(category);
        exercise2.setCategory(category);
        FavouriteExercises favouriteExercises = new FavouriteExercises();
        favouriteExercises.setUser(user);
        favouriteExercises.setUserFavouriteExercise(exercise);
        FavouriteExercises favouriteExercises2 = new FavouriteExercises();
        favouriteExercises2.setUser(user);
        favouriteExercises2.setUserFavouriteExercise(exercise2);

        //When
        usersService.saveUser(user);
        categoriesService.saveCategory(category);
        exerciseService.saveExercise(exercise);
        exerciseService.saveExercise(exercise2);
        favouriteExercisesService.saveNewFavouriteExercise(favouriteExercises);
        favouriteExercisesService.saveNewFavouriteExercise(favouriteExercises2);

        List<FavouriteExercises> getAllExercises = favouriteExercisesService.getUserFavourites(user);
        favouriteExercisesService.deleteUserFavouriteExercise(favouriteExercises2.getId());
        List<FavouriteExercises> getExercisesAfterDelete = favouriteExercisesService.getUserFavourites(user);

        //Then
        Assert.assertEquals(2, getAllExercises.size());
        Assert.assertEquals(1, getExercisesAfterDelete.size());

        //CleanUp
        favouriteExercisesService.deleteAllFavourites();
        exerciseService.deleteAllExercises();
        usersService.deleteAllUsers();
        categoriesService.deleteAllCategories();
    }

    @After
    public void cleanUp() {
        favouriteExercisesService.deleteAllFavourites();
        exerciseService.deleteAllExercises();
        usersService.deleteAllUsers();
        categoriesService.deleteAllCategories();
    }
}
