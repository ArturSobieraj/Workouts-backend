package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.Workouts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface WorkoutsDao extends CrudRepository<Workouts, Long> {

    @Override
    Workouts save(Workouts workout);

    @Override
    void deleteAll();

    List<Workouts> findByExercisingUser(String userName);

    void deleteByWorkoutName(String workoutName);

    Optional<Workouts> findById(Long id);
}
