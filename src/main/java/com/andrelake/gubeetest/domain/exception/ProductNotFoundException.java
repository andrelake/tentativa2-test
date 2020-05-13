package com.andrelake.gubeetest.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String msg) {
		super(msg);
	}
	
	public ProductNotFoundException(Long id) {
		super(String.format("Product with id %d cannot be found.", id));
	}
}
