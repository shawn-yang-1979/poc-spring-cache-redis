package com.example.demo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ScooterDto;
import com.example.demo.entity.Scooter;
import com.example.demo.repo.ScooterRepository;

@Component
public class ScooterDao {

    @Autowired
    private ScooterRepository scooterRepository;

    @CacheEvict("scooters")
    public ScooterDto updateScooter(String id, ScooterDto.Detail scooterDetail) {
        Scooter entity = findScooterById(id);
        entity.setModel(scooterDetail.getModel());
        entity.setOwner(scooterDetail.getOwner());
        return map(entity);
    }

    private Scooter findScooterById(String id) {
        Scooter entity = this.scooterRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return entity;
    }

    @CacheEvict("scooters")
    public ScooterDto createScooter(ScooterDto.Detail scooterDetail) {
        Scooter entity = new Scooter();
        entity.setModel(scooterDetail.getModel());
        entity.setOwner(scooterDetail.getOwner());
        entity = this.scooterRepository.save(entity);
        return map(entity);
    }

    @Cacheable("scooter")
    public ScooterDto getScooterById(String id) {
        Scooter entity = findScooterById(id);
        return map(entity);
    }

    @Cacheable("scooters")
    public List<ScooterDto> getScootersByOwner(String owner) {
        List<ScooterDto> result = new LinkedList<>();
        this.scooterRepository.findByOwner(owner).forEach(entity -> {
            ScooterDto dto = map(entity);
            result.add(dto);
        });
        simulateSlowService();
        return result;
    }

    @Cacheable("scooters")
    public List<ScooterDto> getScootersByModel(String model) {
        List<ScooterDto> result = new LinkedList<>();
        this.scooterRepository.findByModel(model).forEach(entity -> {
            ScooterDto dto = map(entity);
            result.add(dto);
        });
        simulateSlowService();
        return result;
    }

    @Cacheable("scooters")
    public List<ScooterDto> getScooters() {
        List<ScooterDto> result = new LinkedList<>();
        this.scooterRepository.findAll().forEach(entity -> {
            ScooterDto dto = map(entity);
            result.add(dto);
        });
        simulateSlowService();
        return result;
    }

    private ScooterDto map(Scooter entity) {
        ScooterDto dto = new ScooterDto();
        dto.setId(entity.getId().toString());
        dto.getDetail().setModel(entity.getModel());
        dto.getDetail().setOwner(entity.getOwner());
        return dto;
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
