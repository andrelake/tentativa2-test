package com.andrelake.gubeetest.domain.exception;

public class ProductInUseException extends EntityInUseException{

	private static final long serialVersionUID = 1L;

	public ProductInUseException(String msg) {
		super(msg);
	}
	
	public ProductInUseException(Long id) {
		super(String.format("Product with id %d cannot be removed, because has being used.", id));
	}
}
