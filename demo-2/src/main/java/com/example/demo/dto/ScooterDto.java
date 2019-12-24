package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ScooterDto implements Serializable {

	private String id;
	private Detail detail = new Detail();

	@Data
	public static class Detail implements Serializable {
		private String model;
		private String owner;
	}

}
