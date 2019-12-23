package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.dto.UsersDto;
import com.workouts.workoutsbackend.mappers.UsersMapper;
import com.workouts.workoutsbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;

    @GetMapping(value = "get")
    public List<UsersDto> getAllUsers() {
        return usersMapper.mapToUsersDtoList(usersService.getAllUsers());
    }

    @GetMapping(value = "getOne")
    public UsersDto getUserByMail(@RequestParam String userMail) {
        return usersMapper.mapToUsersDto(usersService.getUser(userMail));
    }

    @PostMapping(value = "add", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UsersDto userDto) {
        usersService.saveUser(usersMapper.mapToUsers(userDto));
    }
}
