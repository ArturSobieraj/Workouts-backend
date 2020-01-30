package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Workouts;
import com.workouts.workoutsbackend.domain.dao.WorkoutsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutsService {
    @Autowired
    private WorkoutsDao workoutsDao;

    public Workouts saveWorkout(Workouts workout) {
        return workoutsDao.save(workout);
    }

    public void deleteAllWorkouts() {
        workoutsDao.deleteAll();
    }

    public void deleteWorkoutByName(String workoutName) {
        workoutsDao.deleteByWorkoutName(workoutName);
    }

    public List<Workouts> getUsersWorkouts(String userName) {
        return workoutsDao.findByExercisingUser(userName);
    }

    public Workouts getById(Long id) {
        return workoutsDao.findById(id).orElse(new Workouts());
    }
}
