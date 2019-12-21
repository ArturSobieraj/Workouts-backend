package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Muscles;
import com.workouts.workoutsbackend.domain.dao.MusclesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusclesService {
    @Autowired
    private MusclesDao musclesDao;

    public Muscles saveNewMuscle(Muscles muscle) {
        return musclesDao.save(muscle);
    }

    public void deleteAllMuscles(){
        musclesDao.deleteAll();
    }

    public Optional<Muscles> findByExternalId(Integer externalId) {
        return musclesDao.findByExternalId(externalId);
    }

    public List<Muscles> getAllMuscles() {
        return musclesDao.findAll();
    }

    public long getMusclesCount() {
        return musclesDao.count();
    }
}
