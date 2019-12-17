package com.workouts.workoutsbackend.domain.dao;

import com.workouts.workoutsbackend.domain.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface EquipmentDao extends CrudRepository<Equipment, Long> {

    @Override
    Equipment save(Equipment equipment);

    Optional<Equipment> findByExternalId(Integer externalId);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long id);
}
