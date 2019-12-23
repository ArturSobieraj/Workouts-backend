package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.clients.WgerClient;
import com.workouts.workoutsbackend.clients.YandexClient;
import com.workouts.workoutsbackend.domain.Categories;
import com.workouts.workoutsbackend.domain.Exercises;
import com.workouts.workoutsbackend.domain.Muscles;
import com.workouts.workoutsbackend.mappers.WgerMapper;
import com.workouts.workoutsbackend.services.CategoriesService;
import com.workouts.workoutsbackend.services.ExerciseService;
import com.workouts.workoutsbackend.services.MusclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/wger")
public class WgerController {

    @Autowired
    private WgerClient wgerClient;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private WgerMapper wgerMapper;
    @Autowired
    private MusclesService musclesService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private YandexClient yandexClient;

    @Scheduled(cron = "0 0 5 * * *")
    @GetMapping(value = "categories")
    public void getCategories() {
        List<Categories> categoriesList = wgerMapper.mapToCategoriesList(wgerClient.getWgerCategories());
        if (categoriesService.getCategoriesCount() == 0) {
            categoriesList.forEach(categories -> categoriesService.saveCategory(categories));
        } else {
            categoriesList.forEach(categories -> {
                if (!categoriesService.getCategoryByExternalId(categories.getExternalId()).isPresent()) {
                    categoriesService.saveCategory(categories);
                }
            });
        }
    }

    @Scheduled(cron = "0 0 5 * * *")
    @GetMapping(value = "muscles")
    public void getMuscles() {
        List<Muscles> musclesList = wgerMapper.mapToMuscleList(wgerClient.getWgerMuscles());
        if (musclesService.getMusclesCount() == 0) {
            musclesList.forEach(muscle -> musclesService.saveNewMuscle(muscle));
        } else {
            musclesList.forEach(muscles -> {
                if (!musclesService.findByExternalId(muscles.getExternalId()).isPresent()) {
                    musclesService.saveNewMuscle(muscles);
                }
            });
        }
    }

    @Scheduled(cron = "0 0/30 5 * * *")
    @GetMapping(value = "exercises")
    public void getExercises() {
        List<Exercises> exercisesList = wgerMapper.mapToExercisesList(wgerClient.getWgerExercises());
        exercisesList.forEach(exercises -> {
            exercises.setExerciseName(yandexClient.translationExceptionHandler(exercises.getExerciseName()));
            exercises.setDescription(yandexClient.translationExceptionHandler(exercises.getDescription()));
        });
        if (exerciseService.getExercisesCount() == 0) {
            exercisesList.forEach(exercises -> exerciseService.saveExercise(exercises));
        } else {
            exercisesList.forEach(exercises -> {
                if (exerciseService.getExerciseByName(exercises.getExerciseName()) == null) {
                    exerciseService.saveExercise(exercises);
                }
            });
        }
    }
}
