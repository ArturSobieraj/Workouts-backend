package com.workouts.workoutsbackend.domain;

import com.workouts.workoutsbackend.domain.dao.EquipmentDao;
import com.workouts.workoutsbackend.services.EquipmentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EquipmentTestSuite {
    @Autowired
    private EquipmentService equipmentService;

    @Test
    public void testSaveAndGetEquipment() {
        //Given
        Equipment equipment = new Equipment();
        equipment.setExternalId(1);
        equipment.setEquipmentName("test");

        //When
        equipmentService.saveEquipment(equipment);
        Optional<Equipment> getByExternalId = equipmentService.getEquipmentByExternalId(1);

        //Then
        Assert.assertTrue(getByExternalId.isPresent());

        //CleanUp
        equipmentService.deleteAllEquipment();
    }

    @After
    public void deleteAll() {
        equipmentService.deleteAllEquipment();
    }
}
