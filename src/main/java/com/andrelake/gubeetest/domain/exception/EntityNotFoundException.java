package com.andrelake.gubeetest.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
