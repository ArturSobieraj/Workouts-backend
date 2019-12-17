package com.workouts.workoutsbackend.services;

import com.workouts.workoutsbackend.domain.Equipment;
import com.workouts.workoutsbackend.domain.dao.EquipmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentDao.save(equipment);
    }

    public Optional<Equipment> getEquipmentByExternalId(Integer externalId) {
        return equipmentDao.findByExternalId(externalId);
    }

    public void deleteAllEquipment() {
        equipmentDao.deleteAll();
    }

    public void deleteEquipment(Long id) {
        equipmentDao.deleteById(id);
    }
}
