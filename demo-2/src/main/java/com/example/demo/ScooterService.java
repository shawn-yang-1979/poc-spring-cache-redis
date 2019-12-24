package com.example.demo;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ScooterDto;
import com.example.demo.entity.Scooter;
import com.example.demo.repo.ScooterRepository;

@Service
public class ScooterService {

	@Autowired
	private ScooterRepository scooterRepository;

	@CacheEvict("scooters")
	@Transactional
	public ScooterDto createScooter(ScooterDto.Detail scooterDetail) {
		Scooter entity = new Scooter();
		entity.setModel(scooterDetail.getModel());
		entity.setOwner(scooterDetail.getOwner());
		entity = this.scooterRepository.save(entity);
		ScooterDto result = new ScooterDto();
		result.setId(entity.getId().toString());
		result.getDetail().setModel(entity.getModel());
		result.getDetail().setOwner(entity.getOwner());
		return result;
	}

	@Cacheable("scooters")
	@Transactional
	public List<ScooterDto> getAllScooters(String inputs) {
		List<ScooterDto> result = new LinkedList<>();
		this.scooterRepository.findAll().forEach(entity -> {
			ScooterDto dto = new ScooterDto();
			dto.setId(entity.getId().toString());
			dto.getDetail().setModel(entity.getModel());
			dto.getDetail().setOwner(entity.getOwner());
			result.add(dto);
		});
		simulateSlowService();
		return result;
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
