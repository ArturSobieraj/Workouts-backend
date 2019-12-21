package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.services.MusclesService;
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
public class MusclesTestSuite {
    @Autowired
    private MusclesService musclesService;

    @Test
    public void testSaveAndGetMuscles() {
        //Given
        Muscles muscles = new Muscles();
        muscles.setExternalId(1);
        muscles.setMuscleName("test");

        //When
        musclesService.saveNewMuscle(muscles);
        Optional<Muscles> getByExternalId = musclesService.findByExternalId(1);

        //Then
        Assert.assertTrue(getByExternalId.isPresent());

        //CleanUp
        musclesService.deleteAllMuscles();
    }

    @After
    public void cleanAll() {
        musclesService.deleteAllMuscles();
    }
}
