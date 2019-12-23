package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.Muscles;
import com.workouts.workoutsbackend.services.MusclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MusclesMapper {

    @Autowired
    private MusclesService musclesService;

    public List<String> mapToMusclesNamesList(List<Muscles> musclesList) {
        return musclesList.stream()
                .map(Muscles::getMuscleName)
                .collect(Collectors.toList());
    }

    public List<Muscles> mapToMusclesList(List<String> musclesNamesList) {
        return musclesNamesList.stream()
                .map(musclesName -> musclesService.getMuscleByName(musclesName))
                .collect(Collectors.toList());
    }
}
