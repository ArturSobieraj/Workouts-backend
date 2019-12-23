package com.workouts.workoutsbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsersDto {

    private Long id;
    private String mail;
    private String password;
}
