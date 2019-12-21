package com.workouts.workoutsbackend.domain.wgerDto.exercises;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseResponseDto {

    @JsonProperty("name_original")
    private String exerciseName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private Integer category;

    @JsonProperty("muscles")
    private List<Integer> muscles;

    @JsonProperty("muscles_secondary")
    private List<Integer> secondaryMuscles;

    @JsonProperty("equipment")
    private List<Integer> equipment;
}
