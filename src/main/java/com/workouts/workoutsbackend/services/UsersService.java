package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Users;
import com.workouts.workoutsbackend.domain.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    public Users getUser(String email) {
        return usersDao.findByEmail(email);
    }

    public Users saveUser(Users user) {
        return usersDao.save(user);
    }

    public void deleteUserByMail(String email) {
        usersDao.deleteByEmail(email);
    }

    public void deleteUser(Long id) {
        usersDao.deleteById(id);
    }

    public void deleteAllUsers() {
        usersDao.deleteAll();
    }
}
