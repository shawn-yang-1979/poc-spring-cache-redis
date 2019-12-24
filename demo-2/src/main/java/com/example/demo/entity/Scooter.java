package com.example.demo.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Scooter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String model;
	private String owner;
}
