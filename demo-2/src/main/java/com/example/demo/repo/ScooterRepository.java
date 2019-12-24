package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Scooter;

public interface ScooterRepository extends CrudRepository<Scooter, String> {

}
