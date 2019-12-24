package com.example.demo;

import com.example.demo.dto.ScooterDto;

public class ObjectMother {

	private ObjectMother() {
	}

	public static ScooterDto.Detail newScooterDetail(String model, String owner) {
		ScooterDto.Detail result = new ScooterDto.Detail();
		result.setModel(model);
		result.setOwner(owner);
		return result;
	}
}
