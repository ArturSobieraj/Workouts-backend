package com.workouts.workoutsbackend.domain.wgerDto.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.workouts.workoutsbackend.domain.wgerDto.categories.CategoriesResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WgerCategoriesResponse {

    @JsonProperty("results")
    private List<CategoriesResponseDto> categoriesResponseDtoList;
}
