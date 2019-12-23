package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.Categories;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {

    public String mapToCategoriesName(final Categories categories) {
        return categories.getCategoryName();
    }
}
