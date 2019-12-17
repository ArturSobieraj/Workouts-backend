package com.workouts.workoutsbackend.domain.dao;


import com.workouts.workoutsbackend.domain.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface CategoriesDao extends CrudRepository<Categories, Long> {

    @Override
    Categories save(Categories categories);

    Optional<Categories> findByCategoryName(String categoryName);

    Optional<Categories> findByExternalId(Integer externalId);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}
