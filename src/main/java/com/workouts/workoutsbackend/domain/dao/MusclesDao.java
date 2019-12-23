package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.Muscles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MusclesDao extends CrudRepository<Muscles, Long> {

    @Override
    Muscles save(Muscles muscles);

    @Override
    void deleteAll();

    Optional<Muscles> findByExternalId(Integer externalId);

    Muscles findByMuscleName(String muscleName);

    @Override
    List<Muscles> findAll();

    @Override
    long count();
}
