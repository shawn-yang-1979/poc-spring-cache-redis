package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ScooterDto;

@Component
public class ScooterService {

    @Autowired
    private ScooterDao scooterDao;

    @Transactional
    public ScooterDto updateScooter(String id, ScooterDto.Detail scooterDetail) {
        return scooterDao.updateScooter(id, scooterDetail);
    }

    @Transactional
    public ScooterDto createScooter(ScooterDto.Detail scooterDetail) {
        return scooterDao.createScooter(scooterDetail);
    }

    @Transactional
    public ScooterDto getScooterById(String id) {
        return scooterDao.getScooterById(id);
    }

    @Transactional
    public List<ScooterDto> getScootersByOwner(String owner) {
        return scooterDao.getScootersByOwner(owner);
    }

    @Transactional
    public List<ScooterDto> getScootersByModel(String model) {
        return scooterDao.getScootersByModel(model);
    }

    @Transactional
    public List<ScooterDto> getScooters() {
        return scooterDao.getScooters();
    }

}
