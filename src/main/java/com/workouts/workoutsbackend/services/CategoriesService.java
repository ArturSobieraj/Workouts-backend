package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.dao.CategoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesDao categoriesDao;

    public Categories saveCategory(Categories category) {
        return categoriesDao.save(category);
    }

    public List<Categories> getAllCategories() {
        return categoriesDao.findAll();
    }

    public Optional<Categories> getCategoryByName(String categoryName) {
        return categoriesDao.findByCategoryName(categoryName);
    }

    public Optional<Categories> getCategoryByExternalId(Integer externalId) {
        return categoriesDao.findByExternalId(externalId);
    }

    public void deleteCategory(Long id) {
        categoriesDao.deleteById(id);
    }

    public void deleteAllCategories() {
        categoriesDao.deleteAll();
    }

    public long getCategoriesCount() {
        return categoriesDao.count();
    }
}
