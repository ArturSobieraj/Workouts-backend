package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.domain.dao.UsersDao;
import com.workouts.workoutsbackend.services.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UsersTestSuite {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ExercisesWithParametersService exercisesWithParametersService;
    @Autowired
    private FavouriteExercisesService favouriteExercisesService;

    @Test
    public void testSaveAndGetUser() {
        //Given
        Users user = new Users();
        user.setEmail("test");
        user.setPassword("testPassword");

        //When
        usersService.saveUser(user);
        Users getUser = usersService.getUser("test");

        //Then
        Assert.assertNotNull(getUser);

        usersService.deleteUserByMail("test");
    }

    @After
    public void deleteAll() {
        usersDao.deleteAll();
    }
}
