package com.andrelake.gubeetest.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productName;
	private String description;
	
	@OneToMany(mappedBy = "product")
	private List<TargetMarket> targetMarket = new ArrayList<>();
	
	@OneToMany(mappedBy = "product")
	private List<Stack> stack = new ArrayList<>();
}
