package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.domain.dao.CategoriesDao;
import com.workouts.workoutsbackend.services.CategoriesService;
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
public class CategoriesTestSuite {
    @Autowired
    private CategoriesService categoriesService;

    @Test
    public void testSaveAndGetCategory() {
        //Given
        Categories category = new Categories();
        category.setExternalId(1);
        category.setCategoryName("test");

        //When
        categoriesService.saveCategory(category);
        Optional<Categories> getByName = categoriesService.getCategoryByName("test");
        Optional<Categories> getByExternalId = categoriesService.getCategoryByExternalId(1);

        //Then
        Assert.assertTrue(getByName.isPresent());
        Assert.assertTrue(getByExternalId.isPresent());

        //CleanUp
        categoriesService.deleteAllCategories();
    }

    @After
    public void cleanUp() {
        categoriesService.deleteAllCategories();
    }
}
