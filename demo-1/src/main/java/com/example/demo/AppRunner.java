package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {
    /**
     * Test 2
     */
    @Autowired
    private ScooterDao scooterService;

    @Override
    public void run(String... args) throws Exception {
        this.scooterService.createScooter(ObjectMother.newScooterDetail("G1", "Shawn"));
        this.scooterService.createScooter(ObjectMother.newScooterDetail("G1", "Basil"));
        this.scooterService.createScooter(ObjectMother.newScooterDetail("G2", "Jake"));
        this.scooterService.getScooters().stream().forEach(dto -> log.info(dto.toString()));
//        this.scooterService.getScootersByModel("G1").stream().forEach(dto -> log.info(dto.toString()));
    }

}