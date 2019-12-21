package com.workouts.workoutsbackend.domain.wgerDto.muscles;

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
public class WgerMusclesResponse {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("results")
    private List<MuscleResponseDto> muscleResponseDtoList;
}
