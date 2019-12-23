package com.workouts.workoutsbackend.mappers;

import com.workouts.workoutsbackend.domain.Users;
import com.workouts.workoutsbackend.domain.dto.UsersDto;
import com.workouts.workoutsbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersMapper {
    @Autowired
    private UsersService usersService;

    public List<UsersDto> mapToUsersDtoList(List<Users> users) {
        return users.stream()
                .map(user -> new UsersDto(user.getId(), user.getEmail(), user.getPassword()))
                .collect(Collectors.toList());
    }

    public Users mapToUsers(UsersDto usersDto) {
        return new Users(usersDto.getMail(), usersDto.getPassword());
    }

    public UsersDto mapToUsersDto(Users users) {
        return new UsersDto(users.getId(), users.getEmail(), users.getPassword());
    }
}
