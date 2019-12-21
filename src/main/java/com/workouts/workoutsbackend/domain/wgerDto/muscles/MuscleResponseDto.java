package com.workouts.workoutsbackend.domain.wgerDto.muscles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MuscleResponseDto {

    @JsonProperty("id")
    private Integer externalId;

    @JsonProperty("name")
    private String muscleName;
}
