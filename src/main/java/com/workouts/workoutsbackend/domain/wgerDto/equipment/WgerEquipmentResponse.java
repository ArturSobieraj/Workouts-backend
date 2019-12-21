package com.workouts.workoutsbackend.domain.wgerDto.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WgerEquipmentResponse {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("results")
    private List<EquipmentResponseDto> equipmentList;
}
