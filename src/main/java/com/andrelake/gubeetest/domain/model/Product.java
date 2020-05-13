package com.andrelake.gubeetest.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productName;
	private String description;
	
	@Embedded
	private List<TargetMarket> targetMarket = new ArrayList<>();
	
	@Embedded
	private List<Stack> stack = new ArrayList<>();
	
	public Product() {
	}
}
