package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ScooterDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private ScooterService scooterService;

	@Override
	public void run(String... args) throws Exception {
		createScooter("G1", "Shawn");
		createScooter("G1", "Basil");
		createScooter("G2", "Jake");

		this.scooterService.getAllScooters("123").stream().forEach(dto -> {
			log.info(dto.toString());
		});

		this.scooterService.getAllScooters("2334").stream().forEach(dto -> {
			log.info(dto.toString());
		});
	}

	private void createScooter(String model, String owner) {
		ScooterDto.Detail input = new ScooterDto.Detail();
		input.setModel(model);
		input.setOwner(owner);
		this.scooterService.createScooter(input);
	}

}