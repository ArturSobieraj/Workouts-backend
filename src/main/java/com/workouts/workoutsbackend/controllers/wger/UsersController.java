package com.workouts.workoutsbackend.controllers.wger;

import com.workouts.workoutsbackend.domain.Users;
import com.workouts.workoutsbackend.domain.dto.UsersDto;
import com.workouts.workoutsbackend.mappers.UsersMapper;
import com.workouts.workoutsbackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "add")
    public void addUser(@RequestParam String userName, @RequestParam String password) {
        usersService.saveUser(new Users(userName, password));
    }
}
