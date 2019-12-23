package com.workouts.workoutsbackend.facade.wgerFacade;

import com.workouts.workoutsbackend.controllers.wger.WgerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WgerFacade {

    @Autowired
    private WgerController wgerController;

    public void fetchAllData() {
        wgerController.getMuscles();
        wgerController.getCategories();
        wgerController.getExercises();
    }
}
