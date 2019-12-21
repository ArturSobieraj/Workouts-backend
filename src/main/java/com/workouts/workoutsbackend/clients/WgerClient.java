package com.workouts.workoutsbackend.clients;

import com.workouts.workoutsbackend.domain.wgerDto.categories.CategoriesResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.equipment.EquipmentResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.equipment.WgerEquipmentResponse;
import com.workouts.workoutsbackend.domain.wgerDto.categories.WgerCategoriesResponse;
import com.workouts.workoutsbackend.domain.wgerDto.exercises.ExerciseResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.exercises.WgerExerciseCounter;
import com.workouts.workoutsbackend.domain.wgerDto.exercises.WgerExercisesResponse;
import com.workouts.workoutsbackend.domain.wgerDto.muscles.MuscleResponseDto;
import com.workouts.workoutsbackend.domain.wgerDto.muscles.WgerMusclesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WgerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WgerClient.class);

    @Value("${wger.api.endpoint.prod}")
    private String wgerEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public List<EquipmentResponseDto> getWgerEquipment() {
        URI equipmentURL = UriComponentsBuilder.fromHttpUrl(wgerEndpoint + "/equipment")
                .queryParam("language", 2)
                .queryParam("status", 2).build().encode().toUri();

        try {
            WgerEquipmentResponse equipmentResponse = restTemplate.getForObject(equipmentURL, WgerEquipmentResponse.class);
            return Optional.ofNullable(equipmentResponse.getEquipmentList()).orElse(new ArrayList<>());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<CategoriesResponseDto> getWgerCategories() {
        URI categoriesURL = UriComponentsBuilder.fromHttpUrl(wgerEndpoint + "/exercisecategory")
                .queryParam("language", 2)
                .queryParam("status", 2).build().encode().toUri();

        try {
            WgerCategoriesResponse wgerCategoriesResponse = restTemplate.getForObject(categoriesURL, WgerCategoriesResponse.class);
            return Optional.ofNullable(wgerCategoriesResponse.getCategoriesResponseDtoList()).orElse(new ArrayList<>());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<MuscleResponseDto> getWgerMuscles() {
        URI musclesURL = UriComponentsBuilder.fromHttpUrl(wgerEndpoint + "/muscle")
                .queryParam("language", 2)
                .queryParam("status", 2).build().encode().toUri();

        try {
            WgerMusclesResponse wgerMusclesResponse = restTemplate.getForObject(musclesURL, WgerMusclesResponse.class);
            return Optional.ofNullable(wgerMusclesResponse.getMuscleResponseDtoList()).orElse(new ArrayList<>());
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<ExerciseResponseDto> getWgerExercises() {
        URI countURL = UriComponentsBuilder.fromHttpUrl(wgerEndpoint + "/exercise")
                .queryParam("language", 2)
                .queryParam("status", 2).build().encode().toUri();


        try {
            WgerExerciseCounter wgerExerciseCounter = restTemplate.getForObject(countURL, WgerExerciseCounter.class);

            URI exerciseURL = UriComponentsBuilder.fromHttpUrl(wgerEndpoint + "/exercise")
                    .queryParam("language", 2)
                    .queryParam("status", 2)
                    .queryParam("limit", wgerExerciseCounter.getCount()).build().encode().toUri();

            WgerExercisesResponse wgerExercisesResponse = restTemplate.getForObject(exerciseURL, WgerExercisesResponse.class);
            return Optional.ofNullable(wgerExercisesResponse.getExerciseResponseDtoList()).orElse(new ArrayList<>());
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}

