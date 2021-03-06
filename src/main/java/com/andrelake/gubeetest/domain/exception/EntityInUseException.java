package com.andrelake.gubeetest.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUseException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public EntityInUseException(String msg) {
		super(msg);
	}
}
