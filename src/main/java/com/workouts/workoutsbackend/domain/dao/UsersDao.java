package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UsersDao extends CrudRepository<Users, Long> {

    Users findByEmail(String email);

    @Override
    Users save(Users users);

    void deleteByEmail(String email);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();

    @Override
    List<Users> findAll();
}
