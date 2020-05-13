package com.andrelake.gubeetest.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class TargetMarket {

	@Column(nullable = false)
	private String name;
	
	public TargetMarket() {
	}
}
