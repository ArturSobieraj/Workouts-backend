package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.Users;
import com.workouts.workoutsbackend.domain.Workouts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface WorkoutsDao extends CrudRepository<Workouts, Long> {

    @Override
    Workouts save(Workouts workout);

    @Override
    void deleteAll();

    List<Workouts> getWorkoutsByExercisingUser(Users user);

    void deleteByWorkoutName(String workoutName);


}
